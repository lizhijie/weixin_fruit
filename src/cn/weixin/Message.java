package cn.weixin;

public class Message {
String ToUserName="";
String FromUserName="";
long CreateTime=0;
String MsgType="";
String Content="";
long MsgId=0;
public String getToUserName() {
	return ToUserName;
}
public void setToUserName(String toUserName) {
	ToUserName = toUserName;
}
public String getFromUserName() {
	return FromUserName;
}
public void setFromUserName(String fromUserName) {
	FromUserName = fromUserName;
}
public long getCreateTime() {
	return CreateTime;
}
public void setCreateTime(long createTime) {
	CreateTime = createTime;
}
public String getMsgType() {
	return MsgType;
}
public void setMsgType(String msgType) {
	MsgType = msgType;
}
public String getContent() {
	return Content;
}
public void setContent(String content) {
	Content = content;
}
public long getMsgId() {
	return MsgId;
}
public void setMsgId(long msgId) {
	MsgId = msgId;
}
}
