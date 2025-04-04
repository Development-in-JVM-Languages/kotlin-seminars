# UserDetails

In the [`UserDetailsRepository`](src/main/kotlin/tasks/UserDetails.kt) class, implement the getUserDetails function, which return details of the current user. It should:

Check if the user is available in the database. If so, it should return this data.
If the user is not available in the database, it should asynchronously fetch the user details from the client, use them to create a UserDetails object, and return it.
After fetching new user data, it should asynchronously save it in the database. getUserDetails function should be able to complete its execution without waiting for the data to be saved.


# BestStudent

In the [`BestStudentUseCase`](src/main/kotlin/tasks/BestStudent.kt) class, implement the `getBestStudent` function, which fetches all students from the given semester and then finds the one with the best result.
For this, it first needs to use `StudentsRepository` to find the IDs of students in the semester,
after which it needs to fetch these users’ details. Fetching details should be done asynchronously.
The best user is the one with the highest result value. If there are no students in the given semester,
the function should throw `IllegalStateException`.
