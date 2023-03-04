import React from 'react';
import {
    MDBTable,
    MDBContainer,
    MDBTypography,
    MDBCheckbox,
    MDBTableHead,
    MDBTableBody,
    MDBIcon, MDBBtn
} from 'mdb-react-ui-kit';

function Goal({goal, index, onCheckBoxClicked, onDeleteClicked}) {
    console.log(" See if onDeleteClicked fires")
    //onDeleteClicked()

    console.log(" Goal prop", goal)
    //console.log(" goalHours from prop", prop.goalName)
    console.log(" goalHours length from prop", goal.goalHours.length)
    console.log(" index prop", index)

    const rows = [];

    for (let i = 0; i < goal.goalHours.length; i++) {
        let completed = goal.goalHours[i].completed;

        let timeCompleted = goal.goalHours[i].timeCompleted;
        if (timeCompleted === null) {
            timeCompleted = '';
        } else {
            console.log("formated time completed", new Date( timeCompleted *1000));
        }

        let checkbox = completed ?
            <MDBCheckbox name='flexCheck' onChange={(e) => onCheckBoxClicked(e, index, i)} id='flexCheckDefault' label={i+1} defaultChecked />
            :
            <MDBCheckbox name='flexCheck' onChange={(e) => onCheckBoxClicked(e, index, i)} id='flexCheckDefault' label={i+1} />


        rows.push(
            <tr key={i}>
                <td>{checkbox}</td>
                <td>{timeCompleted}</td>
                <td></td>
            </tr>
        )

    }

    return (
        <MDBContainer fluid className=" border border-1 rounded">
            <br></br>

           {/* <MDBTypography className="text-center" variant='h4'>Goal: <b>{prop.goal.goalName}</b> </MDBTypography>*/}
            <MDBTypography tag='div' className='display-6 pb-3 mb-3 border-bottom text-center'>
                Goal: <b>{goal.goalName}</b>
            </MDBTypography>

            <MDBTable>
                <MDBTableHead>
                    <tr>
                        <td><MDBTypography tag='strong'>Hours</MDBTypography></td>
                        <td><MDBTypography tag='strong'>Date/Time Completed</MDBTypography></td>
                        <td><MDBBtn color='danger' className='text-right' onClick={onDeleteClicked}>
                            <MDBIcon className='me-2' fab icon="ban" /> Delete Goal
                        </MDBBtn></td>
                    </tr>
                </MDBTableHead>
                <MDBTableBody>
                    {rows}
                </MDBTableBody>
            </MDBTable>
        </MDBContainer>
    );
}

export default Goal;