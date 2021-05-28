#Q1
#use search() function to get a list of attached packages, and R objects
search()

#use objects() function to find all possible R builtin functions 
#related to normal distribution
#the package name can be replaced by the index which is 4
#rnorm should be one of them. qqnorm is another one.
objects("package:stats", pattern = "norm")

#generate 100 normal random variables with mean 0 and standard deviation 1
x = rnorm(100)

#produces a normal QQ plot of the values in x
qqnorm(x)

#adds a line to a "theoretical", by default normal, quantile-quantile plot
#which passes through the probs quantiles, by default the first and third
#quartiles
qqline(x)


#Q2
#use sample function to take a sample of the size 100 
#from the elements of 100 without replacement
x = sample(100)

#create a variable sum.val to update value for every loop
sum.val = 0

#for loop to find the sum of a random permutation of 1, 2, to, 100
#as x = sample(100)
for (i in 1:100) {
  sum.val = x[i]+sum.val
}

#print sum.val
print(sum.val)

#compare with sum(x)
#we can find that they are exactly the same
sum(x)

#set min.val to the x[1]
min.val = x[1]

#for loop codes to get the minimum instead of sum 
for (i in 1:100) {
  #compare with min.val and if min.val is bigger than x[i],
  #assign new minimum value to min.val
  if (min.val > x[i]) {
    min.val = x[i]
  }
}

#print min.val
print(min.val)

#compare min.val with min(x)
#and we can find that they are the exactly same
min(x)

#which way is better? min(x) is better because it saves time


#Q3
#forms a vector
N = c(500, 1000, 2000, 4000, 8000)

#initiate or create an empty object
resultsum = NULL

#use for loop to calculate the sum 1/i from 1 to N
for (i in N) {
  #browser()
  resultsum = c(resultsum, sum(1/(1:i)))
}


#show the result
resultsum


#compare with log(N)+0.6, and we can find that there are numerical errors
#between them
print(log(N)+0.6)

#Q4
#create each columns by using rep() function
col.1 = rep(1:8,1)
col.2 = rep(1:2, each=4)
col.3 = rep(rep(1:2, each=2),2)
col.4 = rep(1:2, 4)

#combine every columns
mtrx = cbind(col.1,col.2,col.3,col.4)

#change column name
colnames(mtrx) = c('a','b','c','d')

#show the result
mtrx


#Q5
#(a) Extract the elements of the third row of this data frame
Formaldehyde[3,]

#(b) Extract the elements of the carb (carbohydrate) column
#"carb" can be replaced by 1
Formaldehyde[, "carb"]

#(c) Use the plot function to identify the relation 
#between optden (optical density) and carb.
plot(Formaldehyde[,"carb"],Formaldehyde[,"optden"])

#According to the plot, the relation between optden
#and carb is linear with a positive slope


#Q6
#loads and attach add-on packages
library(MASS)

#(a) Determine the number of rows and columns for this data frame.
nrow(Pima.tr)
ncol(Pima.tr)

#(b) Calculate the median of each column of this data frame with numerical values.

#extract only numeric columns from data frame first
new_df <-Pima.tr[sapply(Pima.tr, is.numeric)]

#calculate the median of each column of this news data frame
median.val <-apply(new_df,2,median)

#print it
print(median.val)

#(c) Find the average bmi for those people who tested Yes for diabetes. 
#Also find the average bmi for those people who tested No for diabetes. 
#Comment on your findings.

#alters the R environment search path by making dataframe variables
#into global variables
attach(Pima.tr)

#Find the average bmi for those people who tested Yes for diabetes
mean(Pima.tr[type=="Yes",]$bmi)

#Find the average bmi for those people who tested No for diabetes.
mean(Pima.tr[type=="No",]$bmi)

#detach it
detach()

#The average bmi for people who tested Yes for diabetes is higher than 
#the average bmi for people who tested No for diabetes.
#this may be because people with diabetes possible have worse health
#than people with no diabetes 

#search() - check for detaching


#Q7
#(a) Find the number of columns and their names for this data frame
ncol(Cars93)
colnames(Cars93)

#(b) Convert the Weight (pounds) column to a factor as 
#"Small" if Weight is between 0 and 2500;
#"Median" if between 2500 and 3500; 
#"Large" if over 3500. 
#Add such a column with name "Car.size" to the data frame Cars93.

#cut the data to categorize it and change the levels of the output factor
Car.size = cut(Cars93$Weight, c(0,2500,3500,10000), 
               labels = c("Small", "Median", "Large"))

#combine together
cbind(Cars93,Car.size)

#(c) Find the mean of MPG.city for those cars in "Small", "Median", 
#or "Large" categories respectively. 
mean(Cars93[Car.size == "Small",]$MPG.city)
mean(Cars93[Car.size == "Median",]$MPG.city)
mean(Cars93[Car.size == "Large",]$MPG.city)

#Bigger cars have smaller MPG because bigger cars use more fuel

