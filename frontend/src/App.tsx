import React, { FC, useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import { User } from './model/User';
import axios from 'axios';
import { Course } from './model/Course';
import { Feedback } from './model/Feedback';
import { Box, Card, CardContent, Typography, FormControlLabel, Checkbox, CardActions, Button } from '@mui/material';

const App: FC = () => {
  const [userList, setUserList] = useState<User[]>();
  const [selectedUser, setUser] = useState<User>();
  const [userCourses, setUserCourses] = useState<Course[]>();
  const [userFeedback, setUserFeedback] = useState<Feedback[]>();

  useEffect(() => {
    axios.get('http://localhost:8080/users/' + selectedUser?.id + '/feedback').then((response) => setUserFeedback(response.data));
  }, [selectedUser])

  useEffect(() => {
    axios.get('http://localhost:8080/users/').then((response) => setUserList(response.data));
  })

  // const reloadUserCourses = () => 
  useEffect(() => {
    axios.get('http://localhost:8080/users/' + selectedUser?.id).then((response) => setUserCourses(response.data));
  }, [selectedUser])

  const hasUserFeedback = (courses: Course[], feedback?: Feedback[]): boolean => {
    if(feedback === undefined){
      return false;
    }
    for (var j = 0; j < courses.length; j++) {
      for (var i = 0; i < feedback.length; i++) {
        if (feedback[i].course === courses[j]){
          return true;
        }
      }
    }
    return false;
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <></>
      </header>
        <Box>
          {userList?.map(user=>
          <Card sx={{margin: 2}}><CardContent><Typography>{user.name}</Typography></CardContent></Card>
          )}
        </Box>
      <Box sx={{ overflow: "auto" }}>
        {userCourses?.map(course =>
          <Card sx={{ margin: 2 }}>
            <CardContent>
              <Typography>{course.name}</Typography>
              <FormControlLabel control={<Checkbox checked={hasUserFeedback(userCourses, userFeedback)} disabled />} label="Feedback was given: " />
              <CardActions>
                {/* <Button onClick={() => onClick(country)}><EditIcon /></Button>
                <Button variant="contained" sx={{ backgroundColor: "red" }} onClick={() => deleteCountry(country)}>Delete</Button> */}
              </CardActions>
            </CardContent>
          </Card>)}
      </Box>
    </div >
  );
}


export default App;
