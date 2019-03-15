# Todo_List

**This is an individual project of the Software Development Academy.**

- An application has been developed to manage tasks.
- The application is a text based user interface via the command-line.
- Each Task has a Title, Due date, Project (that the task belongs to) and a Status (*Done or Not_Done*)
- The user can add and edit tasks, alongside view a list of tasks in various ways. 
- The user is also able to save and load all the data within the application.
- This project incorporated:
 - New java features such as streams and lambda expressions 
 - Unit testing (*JUnit5*)

[Diagrams](https://github.com/Mehdi-Attarpour/Todo_List/tree/dev/Diagram)

### User Manual
1. As soon as the application is run all previous tasks will be loaded and a summary of the tasks will be displayed.
2. The user will see the main menu and can choose to add a task, view tasks, and edit or remove them. The main menu 
window is visible below.
```
>> Welcome to TODO-List APP
>> You have 2 Tasks todo and 0 Tasks are done.
>> Pick an option:
>> (1) Show Tasks
>> (2) Add New Task
>> (3) Edit Task (update, mark as done, remove)
>> (4) Load Tasks
>> (5) Save
>> (6) Quit
```

3. The user is also able to save or load data at any time.
4. Each option may lead to another menu. (*There will be a hint printed on the console in each step regarding how to choose options or what is a valid input.*) The show tasks window is shown below. 

```
>>> How do you want to see the Tasks: 
	>>> (1) Show summary
	>>> (2) Show Done Tasks
	>>> (3) Show ToDo (Not_Done) Tasks
	>>> (4) Show all Tasks sorted by title
	>>> (5) Show all Tasks sorted by due date
	>>> (6) Show all Tasks grouped by project
	>>> (7) Show Task List of a certain project
	>>> (8) Show Task List with certain due date
	>>> (9) Back
hint: Please choose a number in range 1 to 9
```
### Application Rules

1. The user can not add duplicate tasks. An error message is shown below.
```
You already have the same task. Please try again.
```
2. The status of a task is automatically set to Not_Done. A hint message is shown below.
```
Note
The Task status is set as 'Not_Done'.
If you want to change it please choose edit option.
```
3. The user can not add a task with a past due date. An error message is shown below.
```
Check your calendar, you entered a past date, Sorry dude you may missed the deadline.
Enter a future date PLEASE!!!
```
4. Each user input from console must be valid regarding the input type. (*There will be a hint printed on the console showing a valid input format*) for example:
```
Please Enter date in this format! yyyy-MM-dd
```
```
hint: Please choose a number in range 1 to 9
```

#### Author:
- [Mehdi Attarpour](https://github.com/Mehdi-Attarpour)
- [LinkedIn](https://www.linkedin.com/in/mehdi-attarpour-1998549a/)
- [mailto](mailto:attarpour.mehdi@gmail.com)

#### Mentor:
[Nour Alhuda Almajni](https://github.com/nour95)

### Acknowledgments:
- [KTH](https://www.kth.se)
- [Novare Potential](https://www.novarepotential.se)
