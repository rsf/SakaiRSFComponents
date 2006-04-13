find . -name "*.html" | xargs sed -i -e 's/href="%1/href="\/library/'
find . -name "*.html" | xargs sed -i -e 's/src="%1/src="\/library/'