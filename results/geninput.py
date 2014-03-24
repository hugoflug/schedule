import random
import string
import json
from small import *

def id_generator(size=6, chars=string.ascii_uppercase + string.digits):
	return ''.join(random.choice(chars) for _ in range(size))

obj = {}
obj["programs"] = []

for i in range(program_nr):
	new_program = {}
	new_program["program"] = id_generator()
	new_program["courses"] = []
	courses_nr = random.randint(courses_low, courses_high)
	for i in range(courses_nr):
		new_course = {}
		new_course["name"] = id_generator()
		new_course["lessons"] = []
		course_first_teacher = id_generator()
		course_second_teacher = id_generator()
		second_teacher_prob = random.uniform(second_teacher_low, second_teacher_high)
		lessons_nr = random.randint(lessons_low, lessons_high)
		small_cap = random.randint(small_cap_low, small_cap_high)
		big_cap = random.randint(big_cap_low, big_cap_high)
		for i in range(lessons_nr):
			new_lesson = {}
			if random.random() < second_teacher_prob:
				new_lesson["teacher"] = course_first_teacher
			else:
				new_lesson["teacher"] = course_second_teacher
			if random.random() < big_prob:
				new_lesson["capacity"] = big_cap
			else:
				new_lesson["capacity"] = small_cap
			new_course["lessons"].append(new_lesson)
		new_program["courses"].append(new_course)
	obj["programs"].append(new_program)
obj["weeks"] = weeks

print json.dumps(obj, indent=4, separators=(',', ': '))
