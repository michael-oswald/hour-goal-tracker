import React from 'react';
import {MDBTable, MDBContainer, MDBTypography, MDBCheckbox} from 'mdb-react-ui-kit';

export default function Goal(prop, index, onCheckBoxClicked, onDeleteClicked) {
    console.log(" Goal prop", prop)
    //console.log(" goalHours from prop", prop.goalName)
    console.log(" goalHours length from prop", prop.goal.goalHours.length)
    console.log(" index prop", index)

    const rows = [];

    for (let i = 0; i < prop.goal.goalHours.length; i++) {
        let completed = prop.goal.goalHours[i].completed;
        let timeCompleted = prop.goal.goalHours[i].timeCompleted;
        if ( i ===0) {
            console.log("completed in for loop", completed)
            console.log("timeCompleted in for loop", timeCompleted)
        }

        rows.push(<MDBCheckbox name='flexCheck' value='' id='flexCheckDefault' label={i+1} />)
    }

    return (
        <MDBContainer fluid className=" border border-1 rounded">
            <br></br>
            <MDBTable align="middle" className='caption-top'>
                <caption><MDBTypography className="text-center" variant='h4'>Goal: <b>{prop.goal.goalName}</b> </MDBTypography></caption>
                <caption><MDBTypography variant='h6'><b>Hours</b> </MDBTypography></caption>
                {rows}
            </MDBTable>
        </MDBContainer>
    );
}