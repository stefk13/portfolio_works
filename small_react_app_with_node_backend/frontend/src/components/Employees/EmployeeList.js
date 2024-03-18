import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Link } from 'react-router-dom'
import '../../CSS/employeelist.css'

const EmployeeList = () => {
  const [employees, setEmployees] = useState([])

  const fetchEmployees = async () => {
    try {
      const response = await axios.get('http://localhost:3001/api/v1/emp/employees')
      setEmployees(response.data)
    } catch (error) {
      console.error('Fetch employees error:', error)
    }
  }

  useEffect(() => {
    fetchEmployees()
  }, [])

  const handleDeleteEmployee = async (employeeId) => {
    const confirmDelete = window.confirm('Are you sure you want to delete this employee?')
    if (confirmDelete) {
      try {
        await axios.delete(`http://localhost:3001/api/v1/emp/employees/${employeeId}`)
        fetchEmployees()
      } catch (error) {
        console.error('Delete employee error:', error)
      }
    }
  }

  return (
    <div className='empList-container'>
      <div className='empList-content'>
      <h1>Employee List</h1>
      <Link to="/add-employee">
        <button className='addEmp-button'>Add Employee</button>
      </Link>
      <table>
        <thead>
          <tr>
            <th>Employee Name</th>
            <th>Employee Email</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {employees.map((employee) => (
            <tr key={employee._id}>
              <td>{`${employee.first_name} ${employee.last_name}`}</td>
              <td>{employee.email}</td>
              <td>
                <Link to={`/update-employee/${employee._id}`}>
                  <button className='update-btn'>Update</button>
                </Link>
                <button className='delete-btn' onClick={() => handleDeleteEmployee(employee._id)}>Delete</button>
                <Link to={`/view-employee/${employee._id}`}>
                  <button className='view-btn'>View</button>
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      </div>
    </div>
  )
}

export default EmployeeList
