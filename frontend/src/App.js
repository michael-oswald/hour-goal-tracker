import './App.css';

import React from 'react';
import {BrowserRouter as Router, Routes ,Route } from 'react-router-dom';

import { LoginPage } from "./components/LoginPage";
import {GoalPage} from "./components/GoalPage";

export default function App() {
    return (
        <div>
            <Router>
                <Routes>
                    <Route exact path="/" element={<LoginPage/>} />
                    <Route exact path="/goals" element={<GoalPage/>} />
                </Routes>
            </Router>
        </div>
    );
}
