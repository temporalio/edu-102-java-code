# Exercise 3: Testing the Translation Workflow
During this exercise, you will

* Run a unit test provided for the `translateTerm` Activity
* Develop and run your own unit test for the `translateTerm` Activity
* Write assertions for a Workflow test 
* Uncover, diagnose, and fix a bug in the Workflow Definition
* Observe the time-skipping feature in the Workflow test environment

Make your changes to the code in the `practice` subdirectory (look for 
`TODO` comments that will guide you to where you should make changes to 
the code). If you need a hint or want to verify your changes, look at 
the complete version in the `solution` subdirectory.

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

## Part A: Running a Test

We have provided a unit test for the `translateTerm` Activity
to get you started. This test verifies that the Activity correctly 
translates the term "Hello" to German. Take a moment to study the 
test, which you'll find in the `TranslationActivitiesTest.java` file in the
`src/test/java/translationworkflow` directory. Since the test runs the 
Activity, which in turn calls the microservice to do the translation, ensure
that your microservice is running as state above. Then run the test.

1. Run the `mvn test` command to execute the provided test

## Part B: Write and Run Another Test for the Activity

Now it's time to develop and run your own unit test, this time 
verifying that the Activity correctly supports the translation 
of a different word in a different language.

1. Edit the `TranslationActivitiesTest.java` file
2. Copy the `testSuccessfulTranslateActivityHelloGerman` method, 
   renaming the new method as `testSuccessfulTranslateActivityGoodbyeLatvian`
3. Change the term for the input from `hello` to `goodbye` 
4. Change the language code for the input from `de` (German) to `lv` (Latvian)
5. Assert that translation returned by the Activity is `Ardievu`
6. Run `mvn test` again to run this new test, in addition to the others 

## Part C: Test the Activity with Invalid Input

In addition to verifying that your code behaves correctly when used as 
you intended, it is sometimes also helpful to verify its behavior with 
unexpected input. The example below does this, testing that the Activity 
returns the appropriate error when called with an invalid language code. 

```java
@Test
public void testFailedTranslateActivityBadLanguageCode() {
   testEnvironment.registerActivitiesImplementations(new TranslationActivitiesImpl());
   TranslationActivities activity = testEnvironment.newActivityStub(TranslationActivities.class);
   TranslationActivityInput input = new TranslationActivityInput("goodbye", "xq");

   // Assert that an error was thrown and it was an Activity Failure
   Exception exception = assertThrows(ActivityFailure.class, () -> {
      TranslationActivityOutput output = activity.translateTerm(input);
   });

   // Assert that the error has the expected message, which identifies
   // the invalid language code as the cause
   assertTrue(
            exception.getMessage().contains(
                  "An error was caught attempting to call the microservice: Error: Invalid language code \'xq\'"),
            "expected error message");
}
```

Take a moment to study this code, and then continue with the 
following steps:

1. Edit the `TranslationActivitiesTest.java` file
2. Uncomment the imports on lines 4 (`assertThrows`) and 11 (`ActivityFailure`)
3. Copy the entire `testFailedTranslateActivityBadLanguageCode` method
   provided above and paste it at the bottom of the `TranslationActivitiesTest.java` file 
4. Save the changes
5. Run `mvn test` again to run this new test, in addition to the others


## Part D: Test a Workflow Definition

1. Edit the `TranslationWorkflowTest.java` file in the
`src/main/test/java/translationworkflow` directory
4. Add assertions for the following conditions to the `testSuccessfulTranslation` test
   * The `helloMessage` field in the result is `Bonjour, Pierre`
   * The `goodbyeMessage` field in the result is `Au revoir, Pierre`
5. Save your changes
6. Run `mvn test`. This will fail, due to a bug in the Workflow Definition.
7. Find and fix the bug in the Workflow Definition
8. Run the `mvn test` command again to verify that you fixed the bug

There are two things to note about this test.

First, the test completes in a few seconds, even though the Workflow 
Definition contains a `Workflow.Sleep` call that adds a 30-second delay 
to the Workflow Execution. This is because of the time-skipping feature
provided by the test environment.

Second, calls to `registerActivitiesImplementations` near the top of the test indicate 
that the Activity Definitions are executed as part of this Workflow 
test. As you learned, you can test your Workflow Definition in isolation 
from the Activity implementations by using mocks. The optional exercise 
that follows provides an opportunity to try this for yourself.


### This is the end of the exercise.


## (Optional) Using Mock Activities in a Workflow Test

If you have time and would like an additional challenge, 
continue with the following steps.

1. Make a copy of the existing Workflow Test by running 
   `cp TranslationWorkflowTest.java TranslationWorkflowMockTest.java`
2. Edit the `TranslationWorkflowMockTest.java` file
3. Add an import `import static org.mockito.Mockito.*;`
4. Rename the test function to `testSuccessfulTranslationWithMocks`
5. Add the following code to the beginning of the `testSuccessfulTranslationWithMocks` method
```java
TranslationActivities mockedActivities = mock(TranslationActivities.class, withSettings().withoutAnnotations());
when(mockedActivities.translateTerm(new TranslationActivityInput("hello", "fr")))
   .thenReturn(new TranslationActivityOutput("Bonjour"));
```
6. Copy the second line from the above code beginning with `when` and modify it 
to mock the `translateTerm` Activity to return `Au revoir` when `goodbye` is passed
with the `fr` language code specified.
7. Modify the worker registration line to use the new `mockedActivities` instance.
8. Save your changes
9. If you ran the test now as written it would fail. This is because when Java
does a comparison of the `TranslateActivityInput` objects it compares the object
references, not values. To solve this, override the `equals` method in `TranlationActivityInput`
by adding the following code at the bottom.
```java
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TranslationActivityInput other = (TranslationActivityInput) o;
        return this.term.equals(other.term) &&
                this.languageCode.equals(other.languageCode);
    }
```
Typically when you override this method you would also override the `hashCode`
method. For the sake of brevity it has been excluded here.
10. Save your changes
9. Run `mvn test` to run the tests
