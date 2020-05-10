<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>jaxb2-maven-plugin</artifactId>
				<version>1.6</version>
				<executions>
					<execution>
						<id>xjc</id>
						<goals>
							<goal>xjc</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<schemaDirectory>${project.basedir}/src/main/resources/schema</schemaDirectory>
					<outputDirectory>${project.basedir}/src/main/java</outputDirectory>
					<clearOutputDir>false</clearOutputDir>
				</configuration>
			</plugin>

			##########################################################################################
			employee.xsd file
			<?xml version="1.0" encoding="UTF-8"?>
            <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:tns="http://myexample.com/employee"
                       targetNamespace="http://myexample.com/employee">
                <xs:complexType name="employee">
                    <xs:sequence>
                        <xs:element name="name" type="xs:string"/>
                        <xs:element name="age" type="xs:int"/>
                        <xs:element name="organization" type="xs:string"/>
                        <xs:element name="salary" type="xs:decimal"/>
                    </xs:sequence>
                </xs:complexType>
            </xs:schema>
##############################################################
            Encryption

            https://ttux.net/en/post/3des-java-encrypter-des-java-encryption;jsessionid=9FD3D2CE057206F4F0A1259B9C8F4D56