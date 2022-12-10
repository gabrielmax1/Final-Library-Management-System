# Library-Management-System
3rd Year BSc CS, Coursework for JVM module


You have been asked to implement a library management system that supports a local library in storing and sorting details of its book catalogue.
The following information needs to be captured:  
- Book  
  - Title  
  - Author(s)  
  - Year of Publication  
  - Publisher  
  - Subject  
- Author  
  - First name(s)  
  - Surname  
- Publisher  
  - Name  
  ---------------------------------------------------------------------------------------

A book can be written by one or more authors and an author can publish many books.<br />

The system will need to provide the following functionality:  
    - Set up/manage books, authors, publishers  
    - Search for a book or author  
    - List all books written by a particular author  
    - List all books held in the system  
    
Currently, the only type of user of the system will be an administrator.  


-----------------------------------------------------------------------------------------

Implementations:<br />

a) Java GUI for Project Management – Java (10%):<br />
Use Java to create a desktop application with a graphical user interface which enables a user to set up, edit and delete authors, publishers and books.
The application should look pleasant and be easy to use.<br />

b) Object-Oriented Design and Searching - Kotlin (10%)<br />
Create domain and entity classes that create an object-oriented structure supporting the Java GUI application. You should apply separation of concern to ensure that the Java GUI application contains only the user-interface related functionality, and all other responsibility is assigned to the domain and entity classes. These classes should be implemented in Kotlin and integrated into the Java GUI. You should also implement searching functionality.<br />

c) Persistence and Lambda – Kotlin/Java (10%)  
Implement persistence for the book, author and publisher data, which makes it possible to save this data. It is up to you to decide how you wish to save the information (e.g. save it to file or to database). You should use Lambda expressions to manage the collections of data. This should be implemented in Kotlin or Java and integrated into the Java GUI.  

d) Bubble Sort Algorithm - Kotlin (10%)  
Implement an object-oriented component in Kotlin that sorts all books by author and then by book title using a nested bubble sort algorithm. This algorithm should be integrated into the Java GUI, so that when a user lists all books, they are ordered alphabetically.  

e) Merge Sort Algorithm – Kotlin (10%)  
Add implementation for another sorting algorithm, this time using a merge sort. When listing all books, it should be possible for the user to decide whether the bubble sort of merge sort algorithms should be used. However, the result of the sort should be the same.  

f) Radix Sort Algorithm - Scala (10%)  
You implemented the sorting algorithm using an object-oriented approach in Kotlin. Now implement a sorting algorithm using radix sort and functional approach in Scala. Integrate this into the Java GUI and the user should now have the option to choose which of the three sorting algorithms should be used when performing a sort.  

g) Timings – Scala/Kotlin/Java (10%)  
If you haven’t done so already, you should populate your system with many records, so that you can test the speed of the various sorting algorithms you implemented. You can try to import data of books from existing online sources or even write some code to generate random data. You will need sufficient data to measure the execution times of the implemented algorithms and find some meaningful statistical differences.  

Carry out some tests, sorting the same data using the three algorithms and introduce timestamps to obtain the execution times. Display a comparison of the execution times for sorting the data using the three implementations of the sorting algorithms.  

---------------------------------------------------------------------------------
