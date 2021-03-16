function toggleLanguageSelect() {
    let selectButton = $('.language-select__current');

    selectButton.on('click', function () {
        let parent = $(this).closest('.language-select');
        parent.toggleClass('open');
        parent.find('.language-select__dropdown').slideToggle();
    });
}

function mobileMenu() {
    $('.burger-js').on('click', function () {
        let target = $(this);
        $('.mobile-menu').addClass('open');
    });

    $('.burger-close-js').on('click', function () {
        let target = $(this);

        $('.mobile-menu').removeClass('open');
    });
}

function formattedDate(date, options) {
    return date.toLocaleString('ru', options).split('.').reverse().join('-');
}

function getDepartureDate(date) {
    return new Date(date.setFullYear(
        date.getFullYear(),
        date.getMonth(),
        date.getDate() + 1
    ));
}

function dateInputs() {
    let dateInputs = $('.form-control--date');
    let optionsFormattedDate = {
        year: 'numeric',
        month: 'numeric',
        day: 'numeric'
    };
    let dateNow = new Date();

    dateInputs.each(function () {
        let currentItem = $(this);

        if (currentItem.attr('name') === 'departureDate') {
            let defaultDepartureDate = getDepartureDate(dateNow);
            let formattedDepartureDate = formattedDate(defaultDepartureDate, optionsFormattedDate);

            currentItem.attr('min', formattedDepartureDate);
            currentItem.val(formattedDepartureDate);
        } else {
            let defaultArrivalDate = formattedDate(dateNow, optionsFormattedDate);

            currentItem.attr('min', defaultArrivalDate);
            currentItem.val(defaultArrivalDate);
        }
    });

    dateInputs.on('change', function () {
        let inputName = $(this).attr('name');
        let value = $(this).val();

        if (inputName === 'arrivalDate') {
            let departureInput = $('.form-control--date[name="departureDate"]');
            let arrivalDate = new Date(value);
            let departureNextDate = getDepartureDate(arrivalDate);
            let formattedDepartureNextDate = formattedDate(departureNextDate, optionsFormattedDate);

            departureInput.attr('min', formattedDepartureNextDate);
            departureInput.val(formattedDepartureNextDate);
        } else {
            return false;
        }
    });
}

$(document).ready(function () {
    let header = $('.layout-header');
    let offsetHeader = header.height();

    if ($(window).scrollTop() > offsetHeader) {
        header.addClass('layout-header--fixed');
    }

    $(window).on('scroll', function () {
        $('.language-select').removeClass('open');
        $('.language-select__dropdown').slideUp();

        if ($(window).scrollTop() > offsetHeader) {
            header.addClass('layout-header--fixed');
        } else {
            header.removeClass("layout-header--fixed");
        }
    });

    toggleLanguageSelect();
    mobileMenu();
    dateInputs();
});

// document.addEventListener('keydown', (event) => {
//     if (event.keyCode === 116) event.preventDefault();
// })