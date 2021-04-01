<% response.setContentType("text/html; charset=UTF-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<c:catch var="err">
	<c:set var="weatherURL" 
	value="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=${code}"/>
	<c:import var="weather" url="${weatherURL}"/>
	
	<x:parse var="wrss" xml="${weather}"/>
{"pubDate":"<x:out select="$wrss/rss/channel/pubDate"/>",
"wfkor" : "<x:out select="$wrss/rss/channel/item/description/body/data/wfKor"/>",
"temp" : "<x:out select="$wrss/rss/channel/item/description/body/data/temp"/>",
"reh" : "<x:out select="$wrss/rss/channel/item/description/body/data/reh"/>",
"pop" : "<x:out select="$wrss/rss/channel/item/description/body/data/pop"/>",
"x": "<x:out select="$wrss/rss/channel/item/description/header/x"/>",
"y" :"<x:out select="$wrss/rss/channel/item/description/header/y"/>",
"tmx":"<x:out select="$wrss/rss/channel/item/description/body/data/tmx"/>",
"tmn":"<x:out select="$wrss/rss/channel/item/description/body/data/tmn"/>"
}
</c:catch>
<c:if test="${err!=null}">
	${err}
</c:if>