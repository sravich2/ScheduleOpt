from random import randint

class Course:
    def __init__(self, name, modules):
        self.name = name
        self.modules = modules

class Module:
    def __init__(self, name, sections):
        self.name = name
        self.sections = sections

class Section:
    def __init__(self, name, start_time, end_time, location):
        self.name = name
        self.start = start_time
        self.end = end_time
        self.loc = location

        self.group = name[0]
        self.time_required = set(range(start_time, end_time, 5))
   
def get_course_info(required_course_names):
    # This function represents the part where we get all the information 
    # for the courses the user selects.

    # For now, it just provides dummy data for testing

    module_types = ["Lecture", "Discussion", "Quiz"]
    course_id = 0
    courses = []
    for course in required_course_names:
        modules = []
        for module in module_types[:randint(1,3)]:
            sections = []
            duration = randint(1,2) #Make the duration either 1 or 2 hours at random
            for section in range((module_types.index(module)+1)*randint(1,6)):
                course_id+=1
                sname = ("A%d") % course_id
                start_time = randint(6,18)*60
                end_time = start_time + duration*60
                location = (randint(0,100), (0,100))
                section = Section(sname, start_time, end_time, location)
                sections.append(section)
            m = Module(module, sections)
            modules.append(m)
        c = Course(course, modules)   
        courses.append(c)
    return courses

def build_schedules(required_courses):
    course_list = get_course_info(required_courses); 


    ## Do whatever you want here

def main():
    required_courses = ["CS125", "MATH241", "HIST102", "PHYS212", "CHEM102", "CHEM103"]
    courses = get_course_info(required_courses)
    for c in courses:
        print c.name
        for m in c.modules:
            print m.name
 #           for s in m.sections:
 #               print s.name
    
main()
