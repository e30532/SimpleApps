# SimpleS2I
FROM open-liberty

# TODO: Put the maintainer name in the image metadata
LABEL maintainer="Yoshiki Yamada <e30532@jp.ibm.com>"

# TODO: Rename the builder environment variable to inform users about application you provide them
ENV BUILDER_VERSION 1.0

# TODO: Set labels used in OpenShift to describe the builder image
LABEL io.k8s.description="Simple app on OCP" \
      io.k8s.display-name="SimpleS2I" \
      io.openshift.expose-services="9080:http" \
      io.openshift.s2i.destination="/tmp" \
      io.openshift.s2i.scripts-url="image:///opt/s2i"

# TODO: Install required packages here:
# RUN yum install -y ... && yum clean all -y
USER root
RUN   apt-get update \
      && apt-get -y install maven \
      && apt-get -y install openjdk-11-jdk \
      && apt-get clean

# TODO (optional): Copy the builder files into /opt/app-root
# COPY ./<builder_folder>/ /opt/app-root/
COPY --chown=1001:0  server.xml /config/server.xml
COPY --chown=1001:0  pom.xml /tmp/
COPY --chown=1001:0  ./src   /tmp/

# TODO: Copy the S2I scripts to /usr/libexec/s2i, since openshift/base-centos7 image
# sets io.openshift.s2i.scripts-url label that way, or update that label
COPY ./s2i/bin/ /opt/s2i/

# TODO: Drop the root user and make the content of /opt/app-root owned by user 1001
# RUN chown -R 1001:1001 /opt/app-root

RUN mkdir -p /home/default/.m2/repository \
    && chown -R 1001:0 /opt/s2i/ && chmod -R +x /opt/s2i/ \
    && mkdir -p /config/dropins && chown -R 1001:0 /config/dropins \
    && chown -R 1001:0 /home/default/.m2 && chmod g=u /home/default/.m2

ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64

# This default user is created in the openshift/base-centos7 image
USER 1001
