<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.profile.ProfileBean"%>

<!DOCTYPE html>
<html>
<head>
    <title>All Profiles</title>
    <style>
        body{
            font-family: Arial, sans-serif;
            background:#f4f4f4;
            padding:40px;
        }

        h2{
            text-align:center;
        }

        table{
            width:100%;
            border-collapse:collapse;
            background:white;
        }

        th,td{
            border:1px solid #ccc;
            padding:10px;
            text-align:left;
        }

        th{
            background:#273469;
            color:white;
        }

        input[type=text]{
            padding:8px;
            width:250px;
        }

        button{
            padding:8px 15px;
            background:#273469;
            color:white;
            border:none;
            cursor:pointer;
        }

        a{
            text-decoration:none;
        }
    </style>
</head>

<body>

<h2>Student Profiles</h2>

<form action="ViewProfilesServlet" method="get">
    <input type="text"
           name="search"
           placeholder="Search Name / Student ID"
           value="<%=request.getAttribute("search")==null?"":request.getAttribute("search")%>">

    <button type="submit">Search</button>
</form>

<br>

<table>

<tr>
    <th>Name</th>
    <th>Student ID</th>
    <th>Program</th>
    <th>Email</th>
    <th>Hobbies</th>
    <th>Introduction</th>
</tr>

<%
ArrayList<ProfileBean> profiles =
(ArrayList<ProfileBean>)request.getAttribute("profiles");

if(profiles!=null){

    for(ProfileBean p : profiles){
%>

<tr>

<td><%=p.getName()%></td>
<td><%=p.getStudentId()%></td>
<td><%=p.getProgram()%></td>
<td><%=p.getEmail()%></td>
<td><%=p.getHobbies()%></td>
<td><%=p.getIntro()%></td>

</tr>

<%
    }
}
%>

</table>

<br>

<a href="index.html">← Back to Form</a>

</body>
</html>