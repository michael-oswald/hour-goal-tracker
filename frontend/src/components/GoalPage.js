import React, { useState } from "react";
import {MDBCol, MDBContainer, MDBInput, MDBRow, MDBTypography} from "mdb-react-ui-kit";
import {useLocation} from 'react-router-dom';
import { MDBBtn, MDBIcon,    MDBModal,
    MDBModalDialog,
    MDBModalContent,
    MDBModalHeader,
    MDBModalTitle,
    MDBModalBody,
    MDBModalFooter } from 'mdb-react-ui-kit';


export function GoalPage() {

    const location = useLocation();
    const userId = location.state.result.userId;
    const [goalArray, setGoalArray] = useState(location.state.result.goals);

    const [newGoalName, setNewGoalName] = useState('');
    const [newGoalHours, setNewGoalHours] = useState(20);
    const [centredModal, setCentredModal] = useState(false);

    //create some state here that is an array of objects that's initially built with the location.state.result
    //And I just add to it whenever the user adds new goals. And I just save the entire payload to backend
    //everytime an update happens.

    console.log("location",location)

    console.log("empty list?", location.state.result.goals.length === 0)

    if (location.state.result.goals.length === 0) { // empty list, meaning we have no prior goals

    } else {

    }

    const newGoalClicked = () => {
        setCentredModal(!centredModal);
    }

    const handleNewGoalNameChange = (event) => {
        const myValue = event.target.value;
        setNewGoalName(myValue);
    };

    const handleNewGoalHoursChange = (event) => {
        const myValue = event.target.value;
        setNewGoalHours(myValue);
    };

    const handleSavePositionBtnClicked = () => {

        console.log("curr Array", goalArray)
        console.log("curr goal name", newGoalName)
        console.log("curr goal hours", newGoalHours)

        //1. create new object. Then create a new array from existing state array
        let goalHoursArray = []
        for (let i = 0; i < newGoalHours; i++) {
            goalHoursArray.push({completed:false, timeCompleted:null});
        }
        let newGoalObj = {goalName:newGoalName, goalHours:goalHoursArray, timestampCreated:Date.now()};

        let newArray = [...goalArray];
        newArray.push(newGoalObj);

        //2. save that new array into backend rest call, which contains all the stuff...
        console.log("new Array", newArray)
        let updatePayload = {userId:userId, goals:newArray};
        console.log("updatePayload stringified", JSON.stringify(updatePayload));

        fetch('http://localhost:8080/goal', {
            method: 'POST',
            body: JSON.stringify(updatePayload),
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
            },
        })
        .then(
            (result) => {
                console.log("result YOOOOO", result)
                //update state
            },
            (error) => {
                console.log("error", error)
                //todo: alert, or retry, or route to error page
            }
        )

        //3. set new array into state of this component so new goal will be rendered on page.


        /*const newArray = array.map((ele, i) => {
            if (i === index) {
                return {name: newItemName, position: newItemPosition};
            } else {
                return ele;
            }
        });
        setArray(newArray);*/
        setCentredModal(!centredModal)

    };

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
           <MDBModal tabIndex='-1' show={centredModal} setShow={setCentredModal}>
               <MDBModalDialog centered>
                   <MDBModalContent>
                       <MDBModalHeader>
                           <MDBModalTitle>Add New Goal</MDBModalTitle>
                           <MDBBtn className='btn-close' color='none' onClick={newGoalClicked}></MDBBtn>
                       </MDBModalHeader>
                       <MDBModalBody>
                           <b>Goal Name:</b>
                           <MDBInput type='text' onChange={handleNewGoalNameChange}></MDBInput>
                           <br />
                           <b>Number of Hours for Goal:</b>
                           <MDBInput type='text' value={newGoalHours} onChange={handleNewGoalHoursChange}></MDBInput>
                       </MDBModalBody>
                       <MDBModalFooter>
                           <MDBBtn color='secondary' onClick={newGoalClicked}>
                               Close
                           </MDBBtn>
                           <MDBBtn color='success' onClick={handleSavePositionBtnClicked}>Save changes</MDBBtn>
                       </MDBModalFooter>
                   </MDBModalContent>
               </MDBModalDialog>
           </MDBModal>

        </MDBContainer>
    );
}