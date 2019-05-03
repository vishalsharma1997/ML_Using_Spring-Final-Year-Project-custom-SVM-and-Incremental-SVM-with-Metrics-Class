# -*- coding: utf-8 -*-
"""
Created on Thu Apr 11 22:54:42 2019

@author: HP
"""
import numpy as np
#-----------------------------------------------------------------------------------------
class My_Metrics:
    #---------------------------------------------------
    def __init__(self):
        self.true_positive = []
        self.true_negative = []
        self.false_positive = []
        self.false_negative = []
        self.true_positives_length = 0
        self.true_negatives_length = 0
        self.false_negatives_length = 0
        self.false_positives_length = 0
        self.confusion_matrix_exists = False
        #print("\t===================\n\tIn constructor\n\t===================\n")
    
    #---------------------------------------------------
    def confusion_matrix(self,actual,prediction):
        for i in range(len(actual)):
            if actual[i] == prediction[i] and actual[i] == 0:
                self.true_negative.append(i)
            
            if actual[i] == prediction[i] and actual[i] == 1:
                self.true_positive.append(i)
            
            if actual[i] != prediction[i]:
                if actual[i] == 1 and prediction[i] == 0:
                    self.false_negative.append(i)
                if actual[i] == 0 and prediction[i] == 1:
                    self.false_positive.append(i)
        
        self.true_positives_length = len(self.true_positive)
        self.true_negatives_length = len(self.true_negative)
        self.false_negatives_length = len(self.false_negative)
        self.false_positives_length = len(self.false_positive)
        confusionMatrix =  np.array([
                                        [self.true_negatives_length, self.false_positives_length],
                                        [self.false_negatives_length, self.true_positives_length]
                                    ])
        self.confusion_matrix_exists = True
        return(confusionMatrix)
        
    #---------------------------------------------------
    def accuracy_score(self,actual,prediction):
        if not self.confusion_matrix_exists :
            self.confusion_matrix(actual,prediction)
        total_correct_predictions = self.true_negatives_length + self.true_positives_length
        total_incorrect_predictions = self.false_negatives_length + self.false_positives_length
        total_predictions = total_correct_predictions + total_incorrect_predictions
        accuracyScore = total_correct_predictions / total_predictions
        
        return(accuracyScore)
    
    #---------------------------------------------------
    def precision_score(self,actual,prediction):
        if not self.confusion_matrix_exists :
            self.confusion_matrix(actual,prediction)
        
        precisionScore = (self.true_positives_length / (self.true_positives_length + self.false_positives_length))
        
        return(precisionScore)
    
    #---------------------------------------------------
    def recall_score(self,actual,prediction):
        if not self.confusion_matrix_exists :
            self.confusion_matrix(actual,prediction)
        
        recallScore = (self.true_positives_length / (self.true_positives_length + self.false_negatives_length))
        
        return(recallScore)
    
    #---------------------------------------------------
    def f1_score(self,actual,prediction):
        precisionScore = self.precision_score(actual,prediction)
        recallScore = self.recall_score(actual,prediction)
        
        f1_Score = 2 * (precisionScore * recallScore) / (precisionScore + recallScore)
        
        return(f1_Score)
    
# End of Class