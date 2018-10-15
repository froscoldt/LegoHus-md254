<%-- 
    Document   : employeepage.jsp
    Created on : Aug 24, 2017, 6:31:57 AM
    Author     : kasper
--%>

<%@page import="java.util.List"%>
<%@page import="FunctionLayer.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee home page</title>
    </head>
    <body>

        <h1>Hello <%=request.getSession().getAttribute("email")%> </h1>
        You are now logged in as a EMPLOYEE of our wonderful site.

        <form name="employeeedit" action="FrontController" method="POST">
            <input type="hidden" name="command" value="employeeedit">
            <div style="border: 1px solid black;height:20px;width:300px;">
                <table style="width:100%;table-layout: fixed">
                    <tr>
                        <td align="center"> ID </td>
                        <td align="center"> Status </td>
                        <td align="center"> Checked </td>
                    </tr>
                </table>
            </div>
            <div style="border: 1px solid black;height:240px;width:300px;;overflow:auto;">

                <table style="width:100%;table-layout: fixed">
                    <% List<Order> test = (List<Order>) request.getAttribute("emporderlist");%>
                    <%
                        for (Order order : test) {
                            out.println("<tr>");
                            out.println("<td align=\"center\">" + order.getId() + "</td>");
                            out.println("<td align=\"center\">" + order.getStatus() + "</td>");
                            out.println("<td align=\"center\"><input type=\"checkbox\" name=\"checkedorder\" value=\"" + order.getId() + "\"></td>");
                            out.println("</tr>");
                        }
                    %>
                </table>
            </div>
            <input type="submit" value="Change to sent">
        </form>


    </body>
</html>
