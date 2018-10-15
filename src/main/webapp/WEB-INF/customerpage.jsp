<%-- 
    Document   : customerpage
    Created on : Aug 22, 2017, 2:33:37 PM
    Author     : kasper
--%>

<%@page import="FunctionLayer.PieceList"%>
<%@page import="java.util.List"%>
<%@page import="FunctionLayer.Order"%>
<%@page import="FunctionLayer.LogicFacade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Homepage</title>
    </head>
    <body>

        <h1>Hello <%=request.getSession().getAttribute("email")%> </h1>
        You are now logged in as a customer of our wonderful site.
        <br>
        Below are fields to fill in the size of your lego house
        <table style="width:40%">
            <tr>
                <td>
                    <form name="order" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="order">
                        Length:<br>
                        <input type="text" name="length" placeholder="Min. 4 - Max. 1000" value=""> dots
                        <br>
                        Width:<br>
                        <input type="text" name="width" placeholder="Min. 4 - Max. 1000" value=""> dots
                        <br>
                        Height:<br>
                        <input type="text" name="height" placeholder="Min. 1 - Max. 1000" value=""> blocks
                        <br><br>
                        <input type="submit" value="Submit">
                    </form>
                </td>
                <td>
                </td>
            </tr>
            <tr>
                <td>
                    <form name="piecelist" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="piecelist">
                        <h1>Your orders</h1>
                        <table style="border: 1px solid black;width:100%;table-layout:fixed" >
                            <col style="width:16%" span="5" />
                            <tr>
                                <th>ID</th>
                                <th>Length</th>
                                <th>Width</th> 
                                <th>Height</th>
                                <th>Status</th>
                                <th>Vælg til stykliste</th>
                            </tr>
                        </table>
                        <div style="border: 1px solid black;height:120px;width:550px;;overflow:auto;">
                            <table style="width:100%;table-layout: fixed">
                                <%
                                    String a = String.valueOf(request.getSession().getAttribute("id"));
                                    List<Order> test = (List) request.getSession().getAttribute("OrderList");
                                    for (Order elem : test) {
                                        out.println("<tr>");
                                        out.println("<td align=\"center\">" + elem.getId() + "</td>");
                                        out.println("<td align=\"center\">" + elem.getLength() + " prikker</td>");
                                        out.println("<td align=\"center\">" + elem.getWidth() + " prikker</td>");
                                        out.println("<td align=\"center\">" + elem.getHeight() + " klodser</td>");
                                        out.println("<td align=\"center\">" + elem.getStatus() + "</td>");
                                        out.println("<th> <input type=\"radio\" name=\"chooseorder\" value=\"" + elem.getId() + "\">");
                                        out.println("</tr>");
                                    }
                                %>
                            </table>
                        </div> 
                        
                        <input type="submit" value="gå til valgt stykliste">
                    </form>
                </td>
                <td>

                    <% PieceList pieceList = (PieceList) request.getSession().getAttribute("pl");
                        if (pieceList != null) {
                    %>
                    <div id="stykliste">
                        Valgte stykliste:
                        <table style="border: 1px solid black;width:300px">
                            <tr style="border: 1px solid black;">
                                <td>type</td>
                                <td>side 1</td>
                                <td>side 2</td>
                                <td>side 3</td>
                                <td>side 4</td>
                                <td>Total</td>
                            </tr>
                            <tr>
                                <td>2x4</td>
                                <%
                                    for (int i = 0; i < pieceList.getFourByTwo().length; i++) {
                                        out.println("<td>" + pieceList.getFourByTwo()[i] + "</td>");
                                    }
                                %>
                            </tr>
                            <tr>
                                <td>2x2</td>
                                <%
                                    for (int i = 0; i < pieceList.getTwoByTwo().length; i++) {
                                        out.println("<td>" + pieceList.getTwoByTwo()[i] + "</td>");
                                    }
                                %>
                            </tr>
                            <tr>
                                <td>2x1</td>
                                <%
                                    for (int i = 0; i < pieceList.getOneByTwo().length; i++) {
                                        out.println("<td>" + pieceList.getOneByTwo()[i] + "</td>");
                                    }
                                %>
                            </tr>
                        </table>
                    </div>
                    <%
                        } else {
                            // ignore it
                        }
                    %>
                </td>
            </tr>
        </table>
    </body>
</html>
