
import {MDBBtn, MDBCol, MDBContainer, MDBInput, MDBRow, MDBTypography} from "mdb-react-ui-kit";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export function LoginPage() {
    const [email, setEmail] = useState('');
    const navigate = useNavigate();

    const loginClicked = () => {
        console.log("clicked it yo, here is email val", email);
        navigate("/goals")

        //do a rest call to backend to get the data...
        //then do a navigation change to route the user to the GoalPage for them to configure their goals
//look at this: https://stackoverflow.com/questions/64566405/react-router-dom-v6-usenavigate-passing-value-to-another-component

    }

    const handleEmailChange = (event) => {
        const myValue = event.target.value;
        setEmail(myValue);
    };

    return (
        <MDBContainer breakpoint='sm' >
            <MDBTypography tag='div' className='display-1 pb-3 mb-3 border-bottom text-center'>
                Hour Goal Tracker
            </MDBTypography>
            <MDBTypography className='pb-3 mb-3 text-center'>
                Login to setup goals and record the number of hours you put in towards achieving your dreams.
            </MDBTypography>
            <MDBRow>
                <MDBCol md='4'>
                </MDBCol>
                <MDBCol md='4'>

                    <MDBInput value={email} onChange={handleEmailChange} className='mb-4'
                              id='form1Example1' label='Email address' />

                    <MDBBtn block onClick={loginClicked}>
                        Sign in
                    </MDBBtn>

                </MDBCol>
                <MDBCol md='4'>
                </MDBCol>
            </MDBRow>
        </MDBContainer>
    );
}