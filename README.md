# Todo_List

**This is Software Development Academy (SDA5) individual project.**

- This application is developed to manage tasks.
- This application use a text based user interface via the command-line.
- Each Task has Title, Due date, Project (that task belong to) and Status (*Done or Not_Done*)
- It can handle adding tasks, editing them, showing them in various ways, save and load all data.
- This project used:
 - new java features like stream and lambdas
 - Unit test is also provided (*JUnit5*)

[Diagram](www.google.com)

### User Manual
1. As soon as applicarion is run all previous tasks will be loaded. A summary of loaded tasks will be displayed.
2. User will see the main menu and can choose to add task, see the tasks in various ways, edit them or remove.
```
>> Welcome to TODO-List APP
>> You have 2 Tasks todo and 0 Tasks are done.
>> Pick an option:
>> (1) Show Tasks
>> (2) Add New Task
>> (3) Edit Task (update, mark as done, remove)
>> (4) Load Tasks
>> (5) Save
>> (6) Quit```
3. User has ability to save or load date any time.
4. Each option may lead to another menu. (*There will be a hint printed on console in each step regarding how to choose options or what is a valid input.*)
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

### Rules
1. User can not add duplicate tasks (*At least one field must be different*)
```
You already have the same task. Please try again.
```
2. When user add a new task the status is set on Not_Done. (*User can edit it though*)
```
Note
The Task status is set as 'Not_Done'.
If you want to change it please choose edit option.
```
3. User can not add a task with past due date.
```
Check your calendar, you entered a past date, Sorry dude you may missed the deadline.
Enter a future date PLEASE!!!
```
4. Each user input from console must be valid regarding the input type. (*There will be hint printed on console showing valid input format*) for example:
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
