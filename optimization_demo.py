from random import randint

class Course:
    def __init__(self, name, modules):
        self.name = name #Example: CS125, HIST 100
        self.modules = modules #A list of all modules for a course

class Module:
    def __init__(self, name, sections):
        self.name = name #Example: Lecture, Discussion, Lab
        self.sections = sections # A list of sections within each module

class Section:
    def __init__(self, name, start_time, end_time, location):
        self.name = name #Example: BDJ, CDQ
        self.start = start_time
        self.end = end_time
        self.loc = location

        # The group of the section is determined by the first letter in the
        # name. For example, CQK would be grouped as a "C" section. This is
        # important for some classes/departments, because they require you
        # to sign up for sections with the same first letter. This is the 
        # case in a lot of Math/CS/Chem courses, but this does not hold in
        # other departments. For example, the lectures, discussions, and
        # lab sections have no couplings for physics courses.
        self.group = name[0]

        # Time required is  a hash set that represents the time each class uses.
        # Time is broken up into 5 minute blocks, and added to the hash set. 
        # Example: a 12:00 AM - 12:20 AM course would have a set like this:
        # {0, 5, 10, 15} 
        # Each number in the set represents a 5 min block of time the course
        # requires. Example:
        # 0  represents -> 12:00 to just before 12:05
        # 5  represents -> 12:05 to just before 12:10
        # 10 represents -> 12:10 to just before 12:15
        # 15 represents -> 12:15 to just before 12:20
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
    # I'll be implementing an algorithm that should build up valid schedules here
    # this weekend.

def main():
    required_courses = ["CS125", "MATH241", "HIST102", "PHYS212", "CHEM102", "CHEM103"]
    courses = get_course_info(required_courses)
    for c in courses:
        print c.name
        for m in c.modules:
            print m.name
            for s in m.sections:
                print s.name
    
main()
