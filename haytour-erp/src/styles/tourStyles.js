export const tourStyles = {
    header: {
        bgcolor: 'var(--primary-color)',
        color: 'white',
        py: 8, // Increased padding for a more premium look
        textAlign: 'center'
    },
    heroImage: (url) => ({
        height: { xs: '300px', md: '500px' },
        borderRadius: 'var(--border-radius-lg)',
        backgroundImage: `url(${url})`,
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        mt: -6, // Overlaps the header slightly
        boxShadow: '0 20px 40px rgba(0,0,0,0.1)',
        position: 'relative',
        zIndex: 2
    }),
    sectionTitle: {
        fontWeight: 'bold',
        mb: 3,
        color: 'var(--primary-color)'
    },
    descriptionText: {
        lineHeight: 1.8,
        color: 'var(--text-muted)',
        fontSize: '1.1rem'
    },
    getMapUrl: (type) => {
        const locations = {
            walking: "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3048.163827660608!2d44.51028327655611!3d40.18111097147775!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x406abcf9a3c48523%3A0x628389659f13e735!2sRepublic%20Square!5e0!3m2!1sen!2sam!4v1700000000000",
            bus: "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3048.243547141381!2d44.728983176556!3d40.17765187147814!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x406aa10f76378e9b%3A0x803511e6498877e6!2sTemple%20of%20Garni!5e0!3m2!1sen!2sam!4v1700000000000",
            bike: "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d24294.629394602973!2d44.83981245!3d40.7419131!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4040222222222222%3A0x4040222222222222!2sDilijan%20National%20Park!5e0!3m2!1sen!2sam!4v1700000000000"
        };
        return locations[type] || locations.walking;
    },
    mapPaper: {
        height: '450px',
        width: '100%',
        borderRadius: 'var(--border-radius)',
        overflow: 'hidden',
        border: '1px solid rgba(0,0,0,0.05)',
        mb: 8
    },
    bookingContainer: {
        p: 4,
        borderRadius: 'var(--border-radius-lg)',
        bgcolor: 'var(--bg-light)',
        border: '1px solid rgba(0,0,0,0.05)',
        boxShadow: '0 4px 20px rgba(0,0,0,0.03)'
    },
    priceCard: {
        p: 2,
        px: 4,
        borderRadius: 'var(--border-radius-sm)',
        border: '1px solid rgba(0,0,0,0.1)',
        bgcolor: 'var(--card-bg)',
        textAlign: 'center',
        minWidth: '150px'
    }
};
