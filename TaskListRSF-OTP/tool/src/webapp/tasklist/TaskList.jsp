<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>

<f:loadBundle basename="org.sakaiproject.tool.tasklist.bundle.Messages" var="msgs"/>
 
<f:view>
	<sakai:view_container title="Task List Tool">
		<style type="text/css">
			@import url("/sakai-tasklist-tool/css/TaskList.css");
		</style>

		<sakai:view_content>
			<h:form id="tasks">

			 	<sakai:view_title value="#{msgs.task_list_title}"/>

			 	<h:outputText value="Hello, #{TaskListBean.currentUserDisplayName}"/>

			 	<sakai:panel_edit>
			 		<h:inputText value="#{TaskListBean.taskText}" size="60"/>
	 				<h:commandButton value="#{msgs.task_list_add}" action="#{TaskListBean.processActionAdd}" />
			 	</sakai:panel_edit>

			 	<h:dataTable
			 		id="tasklist"
			 		value="#{TaskListBean.allTasks}"
			 		var="entry"
			 		columnClasses="firstColumn,secondColumn,thirdColumn,fourthColumn"
			 		headerClass="headerAlignment"
			 		styleClass="listHier">

					<h:column>
						<f:facet name="header">
							<h:outputText value=""/>
						</f:facet>
						<h:selectBooleanCheckbox id="taskSelect" value="#{entry.selected}" rendered="#{entry.delete}"/>
						<h:outputText value="" rendered="#{not entry.delete}"/>
					</h:column>

					<h:column>
						<f:facet name="header">
							<h:outputText value="#{msgs.task_list_owner}"/>
						</f:facet>
						<h:outputText value="#{entry.task.owner}"/>
					</h:column>

					<h:column>
						<f:facet name="header">
							<h:outputText value="#{msgs.task_list_text}"/>
						</f:facet>
						<h:outputText value="#{entry.task.task}"/>
					</h:column>

					<h:column>
						<f:facet name="header">
							<h:outputText value="#{msgs.task_list_date}"/>
						</f:facet>
						<h:outputText value="#{entry.task.creationDate}">
							<f:convertDateTime type="both" dateStyle="medium" timeStyle="short" />
						</h:outputText>
					</h:column>

				</h:dataTable>

				<sakai:button_bar>
					<sakai:button_bar_item id="deleteTask" action="#{TaskListBean.processActionDelete}" value="#{msgs.task_list_delete}"/>  
                </sakai:button_bar>

			 </h:form>
  		</sakai:view_content>	
	</sakai:view_container>
</f:view> 
