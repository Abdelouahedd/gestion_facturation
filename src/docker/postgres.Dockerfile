FROM postgres:14.1
RUN localedef -i fr_FR -c -f UTF-8 -A /usr/share/locale/locale.alias fr_FR.UTF-8
ENV LANG fr_FR.utf8
CMD ["initdb --locale=fr_FR"]
