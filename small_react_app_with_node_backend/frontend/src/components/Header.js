import React from 'react'
import { Link } from 'react-router-dom'
import '../CSS/header.css';


const Header = () => {
 const username = document.cookie.split('; ')
    .find(cookie => cookie.startsWith('user='))
    ?.split('=')[1]

 const handleLogout = () => {
    document.cookie = 'user=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
    window.location.href = '/'
 };

 return (
    <header className="header">
      <nav>
        <Link to="/">Home</Link>
        <Link to="/signup">Signup</Link>
        <Link to="/login">Login</Link>
        <Link to="/employee-list">Employee List</Link>

        {username ? (
          <div>
            Logged in as {username}
            <Link to="/logout" onClick={handleLogout}>Logout</Link>
          </div>
        ) : null}
      </nav>
    </header>
 )
}

export default Header
