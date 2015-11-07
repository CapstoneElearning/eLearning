# eLearning
eLearning project is to simplyfy the learning courses online, which includes course enrollment,notifications,assignments,assessments, grading, thread gruops/forums,

Search Service (service to search course by dept, subject, program etc):

Backend URLs:
Search by program: http://localhost:8080/search/program/<<search_term_goes_here>>
Search by department: http://localhost:8080/search/department/<<search_term_goes_here>>
Search by subject: http://localhost:8080/search/subject/<<search_term_goes_here>>

Course Service (service to add, retrieve and delete course):

Backend URLs:
Create Course: http://localhost:8080/course/create (you should POST a json string representing a course)
Retrieve Course: http://localhost:8080/course/retrieve/<<course_id_goes_here>>
Delete Course: http://localhost:8080/course/delete/<<course_id_goes_here>>

All the above URLs are REST based endpoints and support HTTP GET except Create Course URL which is HTTP POST.