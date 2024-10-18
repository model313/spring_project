const agreeAddFormSend = () => {
	agreeForm.method="POST";
	agreeForm.action="./agree_add";
	agreeForm.submit();
}

const agreeModFormSend = () => {
	agreeForm.method="POST";
	agreeForm.action="./agree_update";
	agreeForm.submit();
}