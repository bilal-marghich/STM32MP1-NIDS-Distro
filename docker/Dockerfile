FROM ubuntu:22.04

 
ENV DEBIAN_FRONTEND=noninteractive
 
RUN apt-get update && apt-get install -y nano \
    build-essential chrpath cpio debianutils diffstat file gawk gcc git \
    iputils-ping libacl1 liblz4-tool locales python3 python3-git \
    python3-jinja2 python3-pexpect python3-pip python3-subunit socat \
    texinfo unzip wget xz-utils zstd \
    sudo \
    && rm -rf /var/lib/apt/lists/*
 
RUN locale-gen en_US.UTF-8
RUN pip3 install kas
ENV LANG=en_US.UTF-8 LANGUAGE=en_US:en LC_ALL=en_US.UTF-8

# Non-root build user — bitbake refuses to run as root
ARG USERNAME=builder
ARG USER_UID=1000
ARG USER_GID=1000
RUN groupadd --gid ${USER_GID} ${USERNAME} \
    && useradd --uid ${USER_UID} --gid ${USER_GID} -m ${USERNAME} \
    && echo "${USERNAME} ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers
 
USER ${USERNAME}

# Set working directory
WORKDIR /home/builder/project