import React from 'react'
import { Link } from 'react-router-dom'
import '../CSS/home.css';

const Home = () => {
  return (
    <div className="home-container">
      <div className='home-content'>
      <p className="text">Employee Management System</p>
      <p className="text2">Stefan Kepinski - 101356431</p>
      <nav>
        <ul style={{ listStyleType: 'none', padding: 0 }}>
          <li>
            <Link to="/signup"><button>Signup</button></Link>
          </li>
          <li>
            <Link to="/login"><button>Login</button></Link>
          </li>
          <li>
            <Link to="/employee-list"><button>Employee List</button></Link>
          </li>
        </ul>
      </nav>
    </div>
    </div>
  )
}

export default Home
