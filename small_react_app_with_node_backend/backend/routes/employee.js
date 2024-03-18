const express = require("express");
const EmployeeModel = require('../models/Employee');
const routes = express.Router();

// Get All Employees
routes.get("/employees", async (req, res) => {
    try {
        const employeeList = await EmployeeModel.find({});
        res.status(200).json(employeeList);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});

// Add NEW Employee
routes.post("/employees", async (req, res) => {
    try {
        const newEmployee = new EmployeeModel({
            ...req.body
        });
        await newEmployee.save();
        res.status(201).json(newEmployee);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});

// Update existing Employee By Id
routes.put("/employees/:_id", async (req, res) => {
    try {
        const updatedEmployee = await EmployeeModel.findByIdAndUpdate(
            req.params._id,
            req.body,
            { new: true }
        );

        if (!updatedEmployee) {
            return res.status(404).json({ message: "Employee Not Found" });
        }

        res.status(200).json(updatedEmployee);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});

// Delete Employee By ID
routes.delete("/employees/:_id", async (req, res) => {
    try {
        const employee = await EmployeeModel.findOneAndDelete({ _id: req.params._id });
        if (!employee) {
            res.status(404).json({ message: "Employee Not Found" });
        } else {
            res.status(204).json(employee);
        }
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});

// Get Employee By ID
routes.get("/employees/:_id", async (req, res) => {
    try {
        const employee = await EmployeeModel.findById(req.params._id);

        if (!employee) {
            return res.status(404).json({ message: "Employee Not Found" });
        }

        res.status(200).json(employee);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});

module.exports = routes;