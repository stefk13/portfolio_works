import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import axios from 'axios'
import '../../CSS/form.css'

const ViewEmployee = () => {
  const { id } = useParams()
  const [employeeData, setEmployeeData] = useState(null)

  useEffect(() => {
    const fetchEmployeeData = async () => {
      try {
        const response = await axios.get(`http://localhost:3001/api/v1/emp/employees/${id}`)
        setEmployeeData(response.data)
      } catch (error) {
        console.error('Error fetching employee data:', error)
      }
    };

    fetchEmployeeData()
  }, [id]);

  const handleBackToEmployees = () => {
    window.location.href = '/employee-list'
  };

  const handleUpdateEmployee = () => {
    window.location.href = `/update-employee/${id}`
  };

  return (
    <div className='form-container'>
      <div className='form-content'>
      <h1>View Employee</h1>
      {employeeData && (
        <div>
          <p>First Name: {employeeData.first_name}</p>
          <p>Last Name: {employeeData.last_name}</p>
          <p>Email: {employeeData.email}</p>
          <p>Gender: {employeeData.gender}</p>
          <p>Salary: {employeeData.salary}</p>
        </div>
      )}
   
      <button className='updateEmp-btn-view' onClick={handleUpdateEmployee}>Update Employee</button>
      <button className='back-btn-view'onClick={handleBackToEmployees}>Back to Employees</button>
      </div>
    </div>
  )
}

export default ViewEmployee
