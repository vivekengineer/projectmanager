FROM openjdk:8
	ADD /projectmanager-0.0.1-SNAPSHOT.jar projectmanager.jar
	EXPOSE 8089
	RUN bash -c 'touch /projectmanager.jar'
	ENTRYPOINT ["java","-jar","projectmanager-0.0.1-SNAPSHOT.jar"]