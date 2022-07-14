package trello.models;

import trello.IdGenerator;

/**
 * @author naveen.chauhan on 12/07/22
 */
public class Card {
     private  String id;
     private String listId;
     private String name;
     private String description;
     private String assignedUser;

     private static final String DEFAULT_ASSIGNMENT = "UNASSIGNED";
     private static final String NO_DESCRIPTION_PROVIDED = "No Description Provided";

     public void setListId(String listId) {
          this.listId = listId;
     }

     public String getId() {
          return id;
     }

     public String getName() {
          return name;
     }

     public String getDescription() {
          return description;
     }

     public String getAssignedUser() {
          return assignedUser;
     }

     public String getListId() {
          return listId;
     }

     private Card(String name, String listId) {
          this.name = name;
          this.listId = listId;
          this.id = IdGenerator.generateId();
          this.description = NO_DESCRIPTION_PROVIDED;
          this.assignedUser = DEFAULT_ASSIGNMENT;
     }

     public static Card create(String name, String boardId) {
          return new Card(name, boardId);
     }

     public void setId(String id) {
          this.id = id;
     }

     public void setName(String name) {
          this.name = name;
     }

     public void setDescription(String description) {
          this.description = description;
     }

     public void setAssignedUser(String assignedUser) {
          this.assignedUser = assignedUser;
     }

     @Override
     public String toString() {
          return "Card{" +
                  "id='" + id + '\'' +
                  ", name='" + name + '\'' +
                  ", description='" + description + '\'' +
                  ", assignedUser='" + assignedUser + '\'' +
                  '}';
     }

     public void removeAssigment() {
          this.setAssignedUser(DEFAULT_ASSIGNMENT);
     }
}
