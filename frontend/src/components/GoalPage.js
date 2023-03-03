import React from "react";
import {MDBCol, MDBContainer, MDBInput, MDBRow, MDBTypography} from "mdb-react-ui-kit";
import {useLocation} from 'react-router-dom';
import { MDBBtn, MDBIcon } from 'mdb-react-ui-kit';


export function GoalPage() {

    const location = useLocation();
    console.log("location",location)

    console.log("empty list?", location.state.result.goals.length === 0)

    if (location.state.result.goals.length === 0) { // empty list, meaning we have no prior goals

    } else {

    }

    const newGoalClicked = () => {
        console.log("new goal clicked");
    }

    return (
       <MDBContainer breakpoint='sm' >
           <MDBTypography tag='div' className='display-1 pb-3 mb-3 border-bottom text-center'>
               Hour Goal Tracker
           </MDBTypography>
           <MDBTypography className='pb-3 mb-3 border-bottom text-center'>
               Create and Manage your goals below
           </MDBTypography>
           <MDBRow>
               <MDBCol md='1'>
               </MDBCol>
               <MDBCol md='3'>

               <MDBBtn onClick={newGoalClicked}>
                   <MDBIcon className='me-2' fab icon='plus' /> Add New Goal
               </MDBBtn>

               </MDBCol>
               <MDBCol md='3'>
               </MDBCol>
           </MDBRow>

        </MDBContainer>
    );
}