/* Comment : Mencari komponen tombol Logout pada halaman website */
const buttonLogoutElement = document.querySelector('#buttonLogout');

buttonLogoutElement.addEventListener('click', function() {
    /* Comment : Saat tombol Logout di klik, akan kembali ke halaman login */
    goToLogin();
});
