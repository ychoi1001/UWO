#Q1
#create calc function
calc = function(n) {
  
  #error checking on input n
  if (!is.numeric(n)){
    stop("n is not a numeric number")
  }
  
  if (all(n<0)) {
    stop("n should be bigger than 0 because sequence starts by 1")
  }
  
  #create sequences from 1 to n
  x = seq(1:n)
  
  #sum the values
  #use pmin and pmax for getting the parallel maxima and minima 
  #of the input values
  min.sum = sum(pmin(2**x, x**3))
  max.sum = sum(pmax(2**x, x**3))
  
  #return min.sum and max.sum as combine values
  return(c(min.sum, max.sum))
  
}

#given values
n = seq(200, 5000, by = 600)

#returns a list object
lapply(n, calc)


#Q2
#(a) create a function "my.conf" to compute low and high bands
#given a vector x and sig = 3
my.conf = function(x, sig=3) {
  # check if x is a vector or not
  if (!is.vector(x)){
    stop("It should be a vector")
  }
  
  #compute low.band and high.band
  low.band = mean(x)-1.96*sig/sqrt(length(x))
  high.band = mean(x)+1.96*sig/sqrt(length(x))
  
  #return values
  return(c(low.band, high.band))
}

#test function
x=rnorm(100)
my.conf(x)

#(b) if sig is unknown, sig can be replaced by 
#the sample standard deviation of x
my.conf2 = function(x) {
  # check if x is a vector or not
  if (!is.vector(x)){
    stop("It should be a vector")
  }
  
  #compute low.band and high.band
  low.band = mean(x)-1.96*sd(x)/sqrt(length(x))
  high.band = mean(x)+1.96*sd(x)/sqrt(length(x))
  
  #return values
  return(c(low.band, high.band))
}

#test function
my.conf2(x)


#Q3
#create function IQR.outliers to compute IQR, find outliers
#if they exist
IQR.outliers = function(x) {
  #do error checkings on x before any computation
  if (!is.vector(x)) {
    stop("It should be a vector")
  }
  
  if (!is.numeric(x)) {
    stop("It should be numeric")
  }
  
  #sort x
  sorted.x = sort(x)
  
  #get length of x
  n = length(x)
  
  #1st quartile
  first.q = sorted.x[round(.25*n)]
  
  #3rd quartile
  third.q = sorted.x[round(.75*n)]
  
  #compute IQR
  iqr = third.q - first.q
  
  #use 1.5*IQR rule to detect suspected outliers if they exist
  iqr.out = 1.5*iqr
  left.tail = iqr - iqr.out
  right.tail = iqr + iqr.out
  
  #return IQR and outliers from left and/or right tail
  list(iqr, sorted.x[sorted.x < left.tail], sorted.x[sorted.x>right.tail])
  
}

#test my function with the variables weight and Time in ChickWeight
x = ChickWeight$weight
IQR.outliers(x)

y = ChickWeight$Time
IQR.outliers(y)

#test my function with wrong inputs
z = c(1, 2, "a", "b")
IQR.outliers(z)


#Q4
#check working directory
#getwd()

#if working directory is not set correct, set a working directory correctly (use setwd())

#Import the data and look at the first six rows
GLBdf <- read.csv("GLB.Ts+dSST.csv")
head(GLBdf)

#Create another data frame to keep only Years, Jan, ..., Dec 13 columns
glb.df <- GLBdf[,c(1:13)]
head(glb.df)

#create an R function with input x to calculate the mean of x
#without the first element x[1]
cal.mean = function(x) {
  #Do error checking on x
  if(!is.vector(x)) {
    stop("It should be a vector")
  }
  #calculate the mean of x without the first element x[1]
  mean(x[2:length(x)])
}

#use apply to generate a vector of yearly average temperatures from 1880 to 2020
yearly.avg.temp <- apply(glb.df, 1, cal.mean)

#class(yearly.avg.temp)

#look at the first six rows
head(yearly.avg.temp)
#nrow(glb.df)

#plot it as a time series
plot.ts(yearly.avg.temp)

#From 1880 to around 1940, there were not big differences on yearly average temperature.
#However, as time goes, yearly average temperature increases obviously. 

#(c)
#Find the vector temp.1900 for the monthly temperatures from Jan to Dec
#for the year 1900
temp.1900 = as.numeric(glb.df[glb.df$Year==1900, 2:13])
#is.vector(temp.1900)

#Find two vectors temp.1960 and  temp.2020 for the monthly temperatures
#from Jan to Dec for the years 1960 and 2020 respectively
temp.1960 = as.numeric(glb.df[glb.df$Year==1960, 2:13])
#is.vector(temp.1960)

temp.2020 <- as.numeric(glb.df[glb.df$Year==2020, 2:13])
#is.vector(temp.2020)

#Run the following codes
plot.ts(temp.1900, ylim=c(-0.35, 1.25))
lines(temp.1960, col = "blue")
lines(temp.2020, col = "red")

#In 1900 and 1960, the monthly temperatures from Jan to Dec are pretty similar; Both are 
#low.
#However, in 2020, the monthly temperatures from Jan to Dec increased a lot 
#comparing 1900 and 1960.


#Q5
#construct a function "mortgage.payment"
mortgage.payment = function(P, i.r, n) {
  #because i.r is a percentage, it should be divided by 100
  i.r = i.r/100
  
  #calculate the periodic payment
  periodic.p = (P*i.r)/(1-(1+i.r)**(-n))
  
  #return it
  return(periodic.p)
}

#if the initial amount is $50,000, the interest
#rate is 2% and the number of interest conversion periods is 300
mortgage.payment(50000,2,300)
