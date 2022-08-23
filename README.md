# solvd-ta-course

Homework 1:

1) Task 1
   Create simple java class with main method that prints some text.
   Parse array from main method arguments and print its first element.
   Compile and run your class from command line.

2) Task 2
   You have array of numbers [3, 7, 6, 13, 33, 9, -100, 25]
   Print all the values of array using while loop.
   In the same loop find the biggest and the smallest value of an array and then print values afterwards.

3) Task 3
   You have array of numbers [3, 7, 6, 13, 33, 9, -100, 25]
   Sort this array using one of sorting algorithms: Insertion sort, Selection sort, Bubble sort.
   You need to understand idea of each this method but use only one for implementation.

Selection sort:
Repeatedly finds the smallest element in an unsorted array and moves it to the front of the array.
The element swaps positions with the current element at the first position of the unsorted portion of the array.

Homework 2:
I. Create a classes hierarchy. It should include at least 10 classes
Topic: Library(Try to find a books or any other information for person based on their preferences)

II. Override methods from class Object(toString, hashcode, equals) for at least 3 classes from the hierarchy.

Homework 3:
Add to your hierarchies:

- Use polymorphism with the abstract class from the hierarchy.
- Create final class, method, variable.

Homework 4:

- Add 5 interfaces to the existing hierarchy.
- Use polymorphism with an interface from the hierarchy.
- Create a static block, method, variable.

Homework 5:

Task 1: Extend your current classes hierarchy including next points:

- Create 5 custom exceptions.
- Handle exceptions in 2 ways: using throws and using try/catch

Task 2: Improve your current classes hierarchy including next points:

- Replace System.out.print() by “java.util.logging.Logger” usage.
- Use different log levels.
- Configure logging of messages to the console and file at the same time using Java logging configuration.

Homework 6:

- Add 5 collections to your existent hierarchy (as attributes of your entity classes)
  and methods for working with them (add,remove,replace,size, etc.)
- Create custom LinkedList with generic and use it as part of your hierarchy with methods (add,remove,size,print all)

Homework 7:

1. Create new Maven project and move your existent packages/classes there.
2. Add some dependencies from maven central repo into your project’s pom.xml (Apache FileUtils library)
3. Build jar file and deploy to the local repository using maven phases
4. Add and configure 2 Plugins:

- maven compiler plugin http://maven.apache.org/plugins/maven-compiler-plugin/
  Here you’ll need to configure some specific java version for your code compilation
- maven-dependency-plugin http://maven.apache.org/plugins/maven-dependency-plugin/
  Using this plugin you’ll need to build list of dependencies that your project use

5. Run mvn for different phases from the Maven lifecycle. Check the result.

- Note: Push new Maven project into fresh repository in github! Don't forget about .gitignore usage

Homework 8:

1. 
- Prepare some .txt file with some random text and add it to your project.
- Read text from the file and calculate the numbers of the unique words.
- Write the result to another file.
- The main requirement is: using apache StringUtils and FileUtils to implement it with minimum lines of code.
- To get text sample you can copy some article from Wikipedia.

2. 
- Use at least 5 different methods from StringUtils and 5 from FileUtils. 
- To process some strings and some files on your local computer.
- Put code for this task into separate runner class with main() method