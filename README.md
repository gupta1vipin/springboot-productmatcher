# springboot-productmatcher
This is an API developed on SpringBoot for a specific product vector matcher use-case
This is a maven based spring boot project. 


# ProductApp.java file can be used to initiate spring boot application with a simple "java ProductApp" command after compiling the project.

One can use the STS or any other IDE to import the maven project and run the ProductApp.java file as a main program.

In case of any problem, please check that maven depdencies are loaded correctly.


## To get matching Product Tag Vector, you can use URL (passing the contract sample)
http://localhost:8080/fetch-matching-tagvectors-map

## To find the similar products, you can use URL (passing response for above API as request body)
http://localhost:8080/fetch-similar-products/{id}

