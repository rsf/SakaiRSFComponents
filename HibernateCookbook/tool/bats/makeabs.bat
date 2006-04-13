@echo off
find . -name "*.html" | xargs sed -i -e 's/href="\/library/href="%1/'
find . -name "*.html" | xargs sed -i -e 's/src="\/library/src="%1/'