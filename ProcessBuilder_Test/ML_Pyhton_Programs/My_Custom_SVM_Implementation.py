# -*- coding: utf-8 -*-
"""
Created on Sun Apr  7 20:54:35 2019

@author: HP
"""
#Reference :- 
#https://stats.stackexchange.com/questions/23391/how-does-a-support-vector-machine-svm-work
#-----------------------------------------------------------------------------------------
import numpy as np
import sys
from sklearn import datasets
from sklearn import svm
from sklearn import metrics as mt
from sklearn.preprocessing import StandardScaler
from sklearn.preprocessing import MinMaxScaler
from sklearn.preprocessing import Normalizer
from My_Custom_Metrics_Implementation import My_Metrics

#-----------------------------------------------------------------------------------------

def printscore(actual, prediction):
    mmt = My_Metrics()
    print("\nConfusion Matrix : " )
    print(mmt.confusion_matrix(actual,prediction))
    print("\n",end="")
    print("\t\tAccuracy Score   :  " , mmt.accuracy_score(actual,prediction))
    print("\t\tPrecision Score  :  " , mmt.precision_score(actual,prediction))
    print("\t\tRecall Score     :  " , mmt.recall_score(actual,prediction))
    print("\t\tF1 - Score       :  " , mmt.f1_score(actual,prediction),"\n\n")

#-----------------------------------------------------------------------------------------
class MySupportVectorMachine:
    
    #---------------------------------------------------
    def __init__(self):
        self.w_vector = []
        self.bias = 0
        self.support_ = []
            
    #---------------------------------------------------
    def get_support_vectors(self,Xdata,Ydata):
        for i , xi in enumerate(Xdata):
            if Ydata[i] == 0:
                Ydata[i] = -1
            
            #print((Ydata[i] * np.dot(self.w_vector, xi) + self.bias).astype(int),end ="->")
                
            if Ydata[i] == -1:
                Ydata[i] = 0
        #print(self.w_vector)
        
    #---------------------------------------------------
    def fit(self,Xdata,Ydata):
        #Initialize our SVMs weight vector with zeros 
        w = np.zeros(len(Xdata[0]))
        #The learning rate
        eta = 0.0001
        #how many iterations to train for
        epochs = 1000
        #store misclassifications so we can plot how they change over time
        errors = []
        bias = 0
        C = 0.001
        print("\n\nTraining... ",end ="" )
        #training part, gradient descent part
        for epoch in range(1,epochs):
            error = 0
            if epoch %1000 == 0:
                print("." , end = "")
            for i, x in enumerate(Xdata):
                #misclassification
                if Ydata[i] == 0:
                    Ydata[i]= -1

                if (Ydata[i]*np.dot(Xdata[i], w) + bias) < 1:
                    #misclassified update for ours weights
                    w = w + eta * ( C *(Xdata[i] * Ydata[i]) + (-2  *(1/epoch)* w) )
                    bias = bias + eta * (Ydata[i] * C)
                    error = 1
                else:
                    #correct classification, update our weights
                    w = w + eta * (-2  *(1/epoch)* w)
                if Ydata[i] == -1:
                    Ydata[i]= 0
            errors.append(error)
        self.w_vector = w
        self.bias = bias
        print("\n\t\t\tSVM is Successfully Trained...\n")
        
        #self.get_support_vectors(Xdata, Ydata)
    
    #---------------------------------------------------
    def predict(self, XtestData):
        #print("\n\n w_vector: \n",self.w_vector)
        #print("\n\n bias: \n",self.bias)
        predictedDataList = []
        for xi in XtestData:
            predictedData = np.sign(np.dot(self.w_vector,xi) + self.bias).astype(int)
            if predictedData == -1:
                predictedData = 0
            predictedDataList.append(predictedData)
        return(np.array(predictedDataList))
        #pass
    
    #---------------------------------------------------

# End of the Class
#-----------------------------------------------------------------------------------------
if __name__ == '__main__':
    #train_ratio = [.1,.15,.2,.25,.3,.35,.4,.45,.5,.55,.6,.65,.7,.75,.8,.85,.9,.95]
    #for train_test_ratio in train_ratio:
    if True:
        # load datasets
        myDataSet = datasets.load_breast_cancer()
        try :
            print("\nENTER THE TRAIN TEST SPLIT VALUE (between 0 to 1) : " , end = "")
            train_test_ratio = float(input())
            #train_test_ratio = 0.70298769771
            
            #print(myDataSet.data.shape)
            
            rows = myDataSet.data.shape[0]
            n_training_rows = int(train_test_ratio * rows)
            print("NO. OF TRAINING ROWS IS : ", n_training_rows)
            print("NO. OF TESTING ROWS IS : ", rows - n_training_rows)
        except Exception:
            print("\t\tINVALID INPUT !!!")
            sys.exit()
        
        Xdata = myDataSet.data
        Ydata = myDataSet.target
        
        print("\n========================== RESULTS OBTAINTED WITH BUILT-IN SVM : ==========================\n")
        
        mySVM1 = svm.SVC(kernel = "linear")
        mySVM1.fit(Xdata[ : n_training_rows],Ydata[ : n_training_rows])
        predictedDataList1 = mySVM1.predict(Xdata[n_training_rows+1 : ]) 
        
            
        actualDataList = Ydata[n_training_rows+1 : ]
        
        printscore(actualDataList,predictedDataList1)
        
        #print("i = ",train_test_ratio,"acc = " , mt.accuracy_score(predictedDataList1,actualDataList))
    
        #svm1 = svm.SVC(kernel = "linear")
        #svm1.fit(Xdata[:400],Ydata[:400])
        #predictedDataList1 = svm1.predict(Xdata[400:])
        #print("Built-in SVM Predicted : \n", predictedDataList1)
        #print("\n",mySVM1.coef_)
        #print("\n\n",mySVM1.intercept_)
        
        print("\n========================== RESULTS OBTAINTED WITH CUSTOM SVM : ==========================\n")
        
        
        mySVM = MySupportVectorMachine()
        
        Xtrain = Xdata[ : n_training_rows]
        Xtest = Xdata[n_training_rows+1 : ]
        Ytrain = Ydata[ : n_training_rows]
        Ytest = Ydata[n_training_rows+1 : ]
        
        #scaler = MinMaxScaler(feature_range=(0,1))
        #Xtrain = scaler.fit_transform(Xtrain)
        #Xtest = scaler.transform(Xtest)
        
        scaler = StandardScaler()
        Xtrain = scaler.fit_transform(Xtrain)
        Xtest = scaler.transform(Xtest)
        
        #scaler = Normalizer()
        #Xtrain = scaler.fit_transform(Xtrain)
        #Xtest = scaler.transform(Xtest)
        
        mySVM.fit(Xtrain,Ytrain)
        predictedDataList = mySVM.predict(Xtest)
        
        actualDataList = Ytest
        #print("i = ",train_test_ratio,"acc = " , mt.accuracy_score(predictedDataList,actualDataList))
        #print("\n\t\t\t--------------------\n")
        #print("\n\n",mySVM.w_vector)
        #print("\n\n",mySVM.bias)
        #print("\n\nActual : \n" , actualDataList)
        #print("\n\nPredicted : \n", predictedDataList)
        #for i in range(len())
        
        #print("\n\n\t\t\tSVM is successfully Trained...\n")
        
        printscore(actualDataList,predictedDataList)

#-----------------------------------------------------------------------------------------

