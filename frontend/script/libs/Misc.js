/**
 * Parse a date and returns it in a known format
 * @param {String} date the date to parse
 * @returns {String} parsed date
 */
function parseDate(date) {
	var d = new Date(date);
	const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

	return months[d.getMonth() - 1] + " " + d.getDate() + " " + d.getFullYear() + ", " + d.getHours() + ":" + d.getMinutes();
}