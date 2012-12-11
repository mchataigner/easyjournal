<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<% 
session.invalidate();
%>
<jsp:forward page="accueil.jsp"/>
