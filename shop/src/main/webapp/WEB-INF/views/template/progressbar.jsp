<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    int step = request.getAttribute("step") == null ? 1 : (int) request.getAttribute("step");
%>

<div style="display: flex; gap: 10px; margin-bottom: 20px;">
    <%
        for (int i = 1; i <= 3; i++) {
            String bgColor = (i <= step) ? "#4CAF50" : "#e0e0e0"; 
    %>
        <div style="flex: 1; padding: 10px; background-color: <%= bgColor %>; text-align: center; border-radius: 5px;">
            Step <%= i %>
        </div>
    <%
        }
    %>
</div>
