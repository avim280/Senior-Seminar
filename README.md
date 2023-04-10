# Senior-Seminar

Avi Mahajan

My code generated a schedule for students that places them into 5 sessions at 5 time slots that created the fewest number of conflicts. My approach was to weight the sessions that appeared the most on the students' choices. Then I placed the most common ones at different time slots and repeated the most common ones(making sure to avoid same presentors presenting at the same time). Then I added people to each session(based partly on their choices from first of everyone to 5th of everyone and partly randomly). I had to make sure that a person wasn't placed into a session they were in at a previous time or be placed in two sessions at the same time.
