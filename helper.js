//functions that replace the contents of certain jsp table cells with input text boxes for editing.

function changeTitle()
{
	document.getElementById('projectTitle').innerHTML = '<input type="text" id="projectName" name="newProjectName"/>';
}

function changeDate()
{
	document.getElementById('projectDate').innerHTML = '<input type="text" id="projectDue" name="newDate"/>';
}
