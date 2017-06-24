package Util;
/*
*     It needs JQuery and JSF to work
*/
public class Message {

  private String type;
  private String title;
  private String text;
  public String message;
  public int alreadyShowed = 0;

  public Message() {
    
  }

  public int getAlreadyShowed() {
    return alreadyShowed;
  }

  public void setAlreadyShowed(int alreadyShowed) {
    this.alreadyShowed = alreadyShowed;
  }
  
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Message(String type, String title, String text) {
    this.setType(type);
    this.setTitle(title);
    this.setText(text);
  }

  public Message(String type, String text) {
    this.setType(type);
    this.setText(text);
  }

  public void sendMessage() {
     if(type.equals("success")){
        if(title == null){
          this.setMessage("$.toaster({ priority : '" + type + "', title : '<span class=\"glyphicon glyphicon-ok\"></span>', message : '" + text + "'});");
        }else{
          this.setMessage("$.toaster({ priority : '" + type + "', title : '<span class=\"glyphicon glyphicon-ok\"></span> " + title + "', message : '" + text + "'});");
        }
     }else if(type.equals("danger")){
       if(title == null){
          this.setMessage("$.toaster({ priority : '" + type + "', title : '<span class=\"glyphicon glyphicon-remove\"></span>', message : '" + text + "'});");
        }else{
          this.setMessage("$.toaster({ priority : '" + type + "', title : '<span class=\"glyphicon glyphicon-remove\"></span> " + title + "', message : '" + text + "'});");
        }
     }
  }

}
