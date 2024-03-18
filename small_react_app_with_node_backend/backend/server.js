const express = require("express")
const cors = require('cors')
const employeeRoutes = require("./routes/employee")
const userRoutes = require("./routes/user")
const mongoose = require('mongoose')

const app = express()
var v1api = express()

const SERVER_PORT = 3001

app.use(express.json())
app.use(express.urlencoded())

const corsOptions = {
    origin: 'http://localhost:3000',
    credentials: true,
};

app.use(cors(corsOptions));

const DB_CONNECTION_STRING = "mongodb+srv://admin:password921@cluster0.ydy4wmd.mongodb.net/COMP3123_Assignment1?retryWrites=true&w=majority"

mongoose.connect(DB_CONNECTION_STRING, {
    //useNewUrlParser: true,
    //userUnifiedTopology: true
});

var errorHandling = (err, req, res, next) => {
    console.log("Error Handled");
    const errStatus = err.statusCode || 500
    const errMsg = err.message || 'Something went wrong'
    res.status(errStatus).json({
        success: false,
        status: errStatus,
        message: errMsg,
        stack: process.env.NODE_ENV === 'development' ? err.stack : {}
    })
};

app.use(errorHandling);

v1api.use('/user', userRoutes)
v1api.use('/emp', employeeRoutes)
app.use("/api/v1", v1api)

app.route("/")
    .get((req, res) => {
        res.send("<h1>Stefan Kepinski - Assignment 1</h1>")
    });

app.listen(SERVER_PORT, () => {
    console.log(`Server running at http://localhost:${SERVER_PORT}/`)
});