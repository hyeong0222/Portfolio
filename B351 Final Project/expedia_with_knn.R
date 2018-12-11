install.packages("tidyverse") 
library(tidyverse)
install.packages("reshape2")
library(reshape2)
install.packages("dplyr")
require(dplyr)
install.packages("sqldf")
require(sqldf)
require(class)

train = read.csv(file = "C:/Users/Younghun/Desktop/sample_train.csv", header=T, sep = ",")
test = read.csv(file = "C:/Users/Younghun/Desktop/sample_test.csv", header=T, sep = ",")

# removed first column
sample_train <- train
sample_train$X <- NULL
sample_train$is_booking <- NULL
sample_train$cnt <- NULL
sample_train$hotel_cluster <- NULL
sample_test <- test
sample_test$X <- NULL

# This is the function that I implemented.
my_knn <- function(train, test, k) {
  m = nrow(train)
  n = ncol(train)
  
  test$target <- "NA"
  for (i in 1:nrow(test)) {
    print(i)
    # Calculate the distance. 
    distance = c()
    for (j in 1:m) {
      distance[j] = dist(rbind(test[i, 1:(n - 1)] , train[j, 1:(n - 1)]))
    }
    
    # Find k minimum distance. 
    k_min_distance = c()
    for (l in 1:k) {
      k_min_distance[l] = train[which.min(distance), n:n][[1]]
      distance[which.min(distance)] = .Machine$integer.max
    }
    
    # Find the majority and assign it to the test set.
    test[i , n] = names(which.max(table(k_min_distance)))
  }
  return (test)
}
###################################################

###################################################
# We can use the KNN that I implemented, but it takes more than 24 hours
# So, we used the builit in function.

# When we run knn with when k = 3, 5, 10
knn_built_in = knn(sample_train, sample_test, train$hotel_cluster, k = 3)
knn_built_in = knn(sample_train, sample_test, train$hotel_cluster, k = 5)
knn_built_in = knn(sample_train, sample_test, train$hotel_cluster, k = 10)
###################################################
write.csv(knn_built_in, 'results_knn.csv')


cluster_acc <- numeric()

#error rate

for(i in 1:10){
  #Apply knn with k = i
  predict<-knn(sample_train,sample_test,
               train$hotel_cluster,k=i)
  cluster_acc<-c(cluster_acc,
              mean(predict==test$X))
}
