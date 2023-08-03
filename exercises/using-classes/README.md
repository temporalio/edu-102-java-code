# Exercise 1: Using Classes for Data
During this exercise, you will

* Define classes to represent input and output of an Activity Definition
* Update the Activity and Workflow code to use these classes
* Run the Workflow to ensure that it works as expected

Make your changes to the code in the `practice` subdirectory (look for `TODO` comments that will guide you to where you should make changes to the code). If you need a hint or want to verify your changes, look at the complete version in the `solution` subdirectory.

## Prerequisite: Ensure the Microservice is running
If you haven't already started the Translation Microservice used by this exercise, 
do so in a separate terminal.

**Note: If you're using the Gitpod environment to run this exercise you can
skip this step. An instance of the Microservice is already running in your
environment**

1. Navigate to the `utilities` directory at the root level of the course
2. Change directoryies into the `microservice directory`
   1. `cd utilities/microservice`
3. Compile the microservice
   1. `mvn clean compile`
4. Start the microservice 
   1. `mvn exec:java -Dexec.mainClass="translationapi.Microservice"`

## Part A: 
This exercise provides an improved version of the translation Workflow used in Temporal 101. The Workflow has already been updated to follow the best practice of using classes to represent input parameters and return values. You'll apply what you've learned to do the same for the Activity.

Before continuing with the steps below, take a moment to look at `TranslationWorkflowInput.java` and `TranslationWorkflowOutput.java` in the `model` directory to see how the classes are defined for the Workflow. After this, look at the `TranslationWorkflow.java` and `TranslationWorkflowImpl.java` files to see how these values are passed in and used in the Workflow code. Finally, look at the `Starter.java` file to see how the input parameters are created and passed into the Workflow.

Once you're ready to implement something similar for the Activity, continue with the steps below:

## Part A: Define the Activity Classes
First you'll define the classes to used for storing the results of the execution
of your activities.

1. Edit the `TranslationActivityInput.java` file in the `model` directory and finish
defining the `TranslateActivityInput` class 
   1. Define an instance variable named `term` of type `String` and use the final modifier to ensure immutability
   2. Define an instance variable named `languageCode` of type `String` and use the final modifier to ensure immutability
   3. Define a constructor that takes `term` and `languageCode` and stores the values in the appropriate instance variables
   4. Define the public methods `getTerm` and `getLanguageCode` to return the values stored in the instance variable
   5. Save your changes
2. Edit the `TranslationActivityOutput.java` file in the `model` directory and finish
defining the `TranslateActivityOutput` class 
   1. Define a field named `translation` of type `String` and use the final modified to ensure immutability
   2. Define a constructor that takes `translation` and stores the values in the appropriate instance variable
   3. Define a public method `getTranslation` to return the value stored in the instance variable
   4. Save your changes


## Part B: Use the Classes in Your Activity
Now that you have defined the class, you must update the Activity code to use them.

1. Edit the `TranslationActivities.java` file
   1. Replace the last two input parameters in the `translateTerm` method header with the class you defined as input
   2. Replace the return type (String) in the `translateTerm` method header with the name of the class you defined as output
   3. Save your changes
2. Edit the `TranslationActivitiesImpl.java` file
   1. Replace the last two input parameters in the `translateTerm` method with the class you defined as input
   2. Replace the return type (String) in the `translateTerm` method with the name of the class you defined as output 
   3. At the end of the method, create an instance of the `TranslationActivityOutput` class, passing in the result of the `StringBuilder` as a parameter, which holds the translation returned in the microservice.call.  
   4. Return the object of the `TranslationActivityOutput` class created in the previous step
   5. Save your changes


## Part C: Update the Workflow Code
You've now updated the Activity code to use the classes. The next step is to update the Workflow code to use these classes where it passes input to the Activity and access its return value.

1. Edit the `TranslationWorkflow.java` file
   1.
2. 1. Edit the `TranslationWorkflowImpl.java` file
   1. 
2. Add a new line to define a `TranslationActivityInput` struct, populating it with the two fields (term and language code) currently passed as input to the first `ExecuteActivity` call
3. Change the variable type used to access the result the first call to `ExecuteActivity` from `string` to `TranslationActivityOutput`
4. Change that `ExecuteActivity` call to use the struct as input instead of the two parameters it now uses
5. Update the `helloMessage` string so that it is based on the `Translation` field from the Activity output struct
6. Repeat the previous four steps for the second call to `ExecuteActivity`, which translates "Goodbye" 
7. Save your changes


## Part D: Run the Translation Workflow
Now that you've made the necessary changes, it's time to run the Workflow to ensure that it works as expected.

1. Ensure that your Translation Microservice is running in a separate. See the 
prerequisites at the top of this file for instructions. 
2. In another terminal, start the Worker by running `mvn exec:java -Dexec.mainClass="translationworkflow.Worker"`
3. In another terminal, execute the Workflow by running `mvn exec:java -Dexec.mainClass="translationworkflow.Starter" -Dexec.args="Mason de"` (replace `Mason` with your first name), which should display customized greeting and farewell messages in German.

If your code didn't work as expected, go back and double-check your changes, possibly comparing them to the code in the `solution` directory.

It's common for a single Workflow Definition to be executed multiple times, each time using a different input. Feel free to experiment with this by specifying a different language code when starting the Workflow. The translation service currently supports the following languages:

* `de`: German
* `es`: Spanish
* `fr`: French
* `lv`: Latvian
* `mi`: Maori
* `sk`: Slovak
* `tr`: Turkish
* `zu`: Zulu



### This is the end of the exercise.

