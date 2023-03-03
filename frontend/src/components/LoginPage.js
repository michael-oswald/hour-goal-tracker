
import {MDBBtn, MDBCol, MDBContainer, MDBInput, MDBRow, MDBTypography} from "mdb-react-ui-kit";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export function LoginPage() {
    const [email, setEmail] = useState('');
    const navigate = useNavigate();

    const loginClicked = () => {

        //make rest call to backend:

        fetch('http://localhost:8080/goal/' + email, {
            method: 'GET',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
            },
        })
        .then(response => response.json())
        .then(
            (result) => {
                console.log("result", result)
                navigate('/goals',{state:{result}});
            },
            (error) => {
                console.log("error", error)
                //todo: alert, or retry, or route to error page
            }
        )


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