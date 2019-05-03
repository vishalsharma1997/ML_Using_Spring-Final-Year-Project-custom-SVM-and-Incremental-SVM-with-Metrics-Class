# -*- coding: utf-8 -*-
"""
Created on Thu Nov 29 22:37:34 2018

@author: HP
"""

"""
    SVM Classifier Practice
"""


from sklearn import svm
import matplotlib.pyplot as plt
from sklearn import datasets
from My_Custom_Metrics_Implementation import My_Metrics
import sys

#------------------------------------------------------------------------------------------------------------

def printscore(actual, prediction):
    mmt = My_Metrics()
    print("\nConfusion Matrix : " )
    print(mmt.confusion_matrix(actual,prediction))
    print("\n",end="")
    print("\t\tAccuracy Score   :  " , mmt.accuracy_score(actual,prediction))
    print("\t\tPrecision Score  :  " , mmt.precision_score(actual,prediction))
    print("\t\tRecall Score     :  " , mmt.recall_score(actual,prediction))
    print("\t\tF1 - Score       :  " , mmt.f1_score(actual,prediction),"\n\n")
    
#------------------------------------------------------------------------------------------------------------
# load datasets
myDataSet = datasets.load_breast_cancer()
try :
    #print("\n\n\t\t\t\tSAMPLE 1 : ")
    print("\nENTER THE TRAIN TEST SPLIT VALUE (between 0 to 1) : " , end = "")
    train_test_ratio = float(input())
    #train_test_ratio = 0.70298769771
    
    print(myDataSet.data.shape)
    
    rows = myDataSet.data.shape[0]
    n_training_rows = int(train_test_ratio * rows)
    print("NO. OF TRAINING ROWS IS : ", n_training_rows)
    print("NO. OF TESTING ROWS IS : ", rows - n_training_rows)
    
    print("ENTER THE NO. OF SUBSETS : ", end = "")
    n_subsets = int(input())
    #n_subsets = 4
    each_subset_size = round(n_training_rows / n_subsets)               # used round() function
    print("\nEACH SUBSET SIZE IS : ", each_subset_size)
    baseValue = [0,0]
    for i in range(2,n_subsets+1):
        baseValue.append(baseValue[i-1] + each_subset_size)
    #print("BaseValue : ", baseValue)
except Exception:
    print("\t\tINVALID INPUT !!!")
    sys.exit()
#---------------------------------------------------------------------------------------------------
train_data = [[None]]
train_ydata = [[None]]

for i in range(1,n_subsets):  # for all dataset except last training dataset
    train_data.append(list(myDataSet.data[baseValue[i] : baseValue[i] + each_subset_size]))
    train_ydata.append(list(myDataSet.target[baseValue[i] : baseValue[i] + each_subset_size]))
train_data.append(list(myDataSet.data[baseValue[i+1] : n_training_rows]))
train_ydata.append(list(myDataSet.target[baseValue[i+1] : n_training_rows]))    
test_data = list(myDataSet.data[n_training_rows:])
test_ydata = list(myDataSet.target[n_training_rows:])
flag = False
#--------------------------------------------------------------------------------------------------

for _ in range(2):

    #--------------------------------------------------------------------------------------------------
    
    final_support_vector_set = []
    final_positive_support_vector_set = []
    final_negative_support_vector_set = []
    support_vector_difference_set = []
    support_vector_set = [[None]]
    positive_support_vector_set = [[None]]
    negative_support_vector_set = [[None]]
    clf = [[None]]
    prediction = [None]
    positive_class_data_index_of_data = [[None]]
    negative_class_data_index_of_data = [[None]]
    
    #--------------------------------------------------------------------------------------------------
    
    for i in range(1,n_subsets+1) :                          #indexing start from 1
        clf.append([])
        clf[i] = svm.SVC(kernel = "linear") # linear Kernel
        clf[i].fit(train_data[i] , train_ydata[i])
        support_vector_set.append([])
        positive_support_vector_set.append([])
        negative_support_vector_set.append([])
        support_vector_set[i] = list( clf[i].support_ + [baseValue[i]]*len(clf[i].support_))
        for j in support_vector_set[i] :
            if myDataSet.target[j] == 1:
                positive_support_vector_set[i].append(j)
            else :
                negative_support_vector_set[i].append(j)
        
    #-------------------------------------------------------------------------------------------------
    
    for i in range(1,n_subsets+1):                  #for the first to dataset , algorithm line 2 and 3 of execute
        positive_class_data_index_of_data.append([])
        negative_class_data_index_of_data.append([])
        prediction.append(None)
        
        prediction[i] = clf[i].predict(train_data[i])
        for j in range(len(prediction[i])):
            if prediction[i][j] == 1 :
                positive_class_data_index_of_data[i].append(j)
            else :
                negative_class_data_index_of_data[i].append(j)
        
        
        final_support_vector_set = final_support_vector_set + support_vector_set[i]
        final_positive_support_vector_set = final_positive_support_vector_set + positive_support_vector_set[i]
        final_negative_support_vector_set = final_negative_support_vector_set + negative_support_vector_set[i]
    
    #------------------------------------------------------------------------------------------------------
    
    temporary_positive_class_data_index_of_data = [[None]]
    temporary_negative_class_data_index_of_data = [[None]]
    predicted_ydata = [None]
        
    temporary_positive_class_data_index_of_data.append([])
    temporary_negative_class_data_index_of_data.append([])
    predicted_ydata.append(None)
                                    # This is done only for the first data
    #------------------------------------------------------------------------------------------------------
    i=1
    while(i <= n_subsets-1):
        support_vector_data_temporary = []
        support_vector_ydata_temporary = []
        
        for j in final_support_vector_set :
            support_vector_data_temporary.append(myDataSet.data[j])
            support_vector_ydata_temporary.append(myDataSet.target[j])
            
        classifier_temporary = svm.SVC(kernel = "linear")
        classifier_temporary.fit(support_vector_data_temporary,support_vector_ydata_temporary)
        
        predicted_ydata[i] = classifier_temporary.predict(train_data[i])
    
        #printscore(train_ydata[1],predicted_ydata1)
    
    
        for j in range(len(predicted_ydata[i])):
            index = baseValue[i] + j
            if predicted_ydata[i][j] == 1 :
                temporary_positive_class_data_index_of_data[i].append(index)
            else :
                temporary_negative_class_data_index_of_data[i].append(index)
    
    
    
        temporary_positive_class_data_index_of_data.append([])
        temporary_negative_class_data_index_of_data.append([])
        predicted_ydata.append(None)
        
        predicted_ydata[i+1] = classifier_temporary.predict(train_data[i+1])
                
        for j in range(len(predicted_ydata[i+1])):
            index = baseValue[i+1] + j
            if predicted_ydata[i+1][j] == 1 :
                temporary_positive_class_data_index_of_data[i+1].append(index)
            else :
                temporary_negative_class_data_index_of_data[i+1].append(index)
            
            #printscore(train_ydata[2],predicted_ydata2)       
    
    #data1_temporary_data_index = data1_temporary_positive_class_data_index + data1_temporary_negative_class_data_index
    #data2_temporary_data_index = data2_temporary_positive_class_data_index + data2_temporary_negative_class_data_index
    
        x11,x12,x21,x22,x31,x32,x41,x42 = [],[],[],[],[],[],[],[]
        
    
    
        x11 = sorted(list(set(positive_class_data_index_of_data[i]).difference(set(temporary_positive_class_data_index_of_data[i]))))
        x12 = sorted(list(set(temporary_positive_class_data_index_of_data[i]).difference(set(positive_class_data_index_of_data[i]))))
        x21 = sorted(list(set(positive_class_data_index_of_data[i+1]).difference(set(temporary_positive_class_data_index_of_data[i+1]))))
        x22 = sorted(list(set(temporary_positive_class_data_index_of_data[i+1]).difference(set(positive_class_data_index_of_data[i+1]))))
        x31 = sorted(list(set(negative_class_data_index_of_data[i]).difference(set(temporary_negative_class_data_index_of_data[i]))))
        x32 = sorted(list(set(temporary_negative_class_data_index_of_data[i]).difference(set(negative_class_data_index_of_data[i]))))
        x41 = sorted(list(set(negative_class_data_index_of_data[i+1]).difference(set(temporary_negative_class_data_index_of_data[i+1]))))
        x42 = sorted(list(set(temporary_negative_class_data_index_of_data[i+1]).difference(set(negative_class_data_index_of_data[i+1]))))
    
        
        support_vector_difference_set = x11 + x12 + x21 + x22
        
        #print(len(support_vector_difference_set),"----------------- ",end = "")
        
        if flag == True:
            support_vector_difference_set = list(set(support_vector_difference_set + x31 + x32 + x41 + x42))
        
        #print(len(support_vector_difference_set), '---')
        
        final_support_vector_set = final_support_vector_set + support_vector_difference_set
        i+=1
    #-----------------------------------------------------------------------------------------------------
    
    if flag == True:
        print("\n================= RESULTS OBTAINTED WITH NEW PROPOSED HEURISTICS : =================\n")
        support_vector_difference_set = list(set(support_vector_difference_set + x31 + x32 + x41 + x42))
    else:
        flag = True
        print("\n====================== RESULTS OBTAINTED WITH OLDER HEURISTICS : ===================")
    support_vector_data_temporary = []
    support_vector_ydata_temporary = []
    
    for i in final_support_vector_set :
        support_vector_data_temporary.append(myDataSet.data[i])
        support_vector_ydata_temporary.append(myDataSet.target[i])
    
    classifier_temporary = svm.SVC(kernel = "linear")
    classifier_temporary.fit(support_vector_data_temporary,support_vector_ydata_temporary)
    
    printscore(test_ydata,classifier_temporary.predict(test_data))
    #clf11 = svm.SVC(kernel="linear").fit(myDataSet.data[:200],myDataSet.target[:200])
    #printscore(myDataSet.target[400:],clf11.predict(myDataSet.data[400:]))
    
    
    # get number of support vectors for each class
    print("\n No. of Support vectors for each class : " , classifier_temporary.n_support_)
    print("\n\t For Positive (+ve) class : " , classifier_temporary.n_support_[1])
    print("\t For Negative (-ve) class : " , classifier_temporary.n_support_[0])
    
