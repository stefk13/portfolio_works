import React from 'react'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import Home from './components/Home'
import Login from './components/Login'
import Signup from './components/Signup'
import EmployeeList from './components/Employees/EmployeeList'
import AddEmployee from './components/Employees/AddEmployee'
import ViewEmployee from './components/Employees/ViewEmployee'
import UpdateEmployee from './components/Employees/UpdateEmployee'
import Header from './components/Header'

const App = () => {
 const userCookie = document.cookie.split('; ')
    .find(cookie => cookie.startsWith('user='))?.split('=')[1]

 return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/login" element={<Login />} />
        {userCookie ? (
          <>
            <Route path="/employee-list" element={<EmployeeList />} />
            <Route path="/add-employee" element={<AddEmployee />} />
            <Route path="/view-employee/:id" element={<ViewEmployee />} />
            <Route path="/update-employee/:id" element={<UpdateEmployee />} />
          </>
        ) : (
          <Route path="/*" element={<Login />} />
        )}
      </Routes>
    </Router>
 )
}

export default App